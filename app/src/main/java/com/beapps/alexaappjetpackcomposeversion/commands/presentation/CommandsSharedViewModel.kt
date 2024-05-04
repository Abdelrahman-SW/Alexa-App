package com.beapps.alexaappjetpackcomposeversion.commands.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.commands.domain.CommandsRepo
import com.beapps.alexaappjetpackcomposeversion.commands.domain.SpeakerManager
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommandsSharedViewModel @Inject constructor(
    private val commandsRepo: CommandsRepo,
    private val speakerManager: SpeakerManager
) : ViewModel() {


    var commandCategories by mutableStateOf(listOf<CommandCategory>())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isAddToFavouriteToLoading by mutableStateOf(false)
        private set

    var isSearchBarActive by mutableStateOf(false)
        private set

    var searchHistory = mutableStateListOf<String>()
        private set

    var currentCategory = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val commandDetails = currentCategory.flatMapLatest {
        if(it == "Favorite") {
            commandsRepo.getAllFavouriteCommands()
        }
        else
        commandsRepo.getAllCommandsForSpecificCategory(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())


    var searchQuery = MutableStateFlow("")

    val searchedCommands = searchQuery.map {
        if (it.isBlank()) listOf<CommandDetails>()
        commandsRepo.searchCommands(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    val isSearchingActive = searchQuery.map {
        it.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    init {
        speakerManager.setup()
        viewModelScope.launch {
            isLoading = true
            commandCategories = commandsRepo.getAllCommandsCategories()
            if (!commandsRepo.isCommandsSavedInDb()) {
                commandsRepo.extractAndSaveAllCommandsInDb()
            }
            isLoading = false
        }
    }

    fun onSelectedCategory(category: String) {
        currentCategory.value = category
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    fun onSearchTrailingIconClicked() {
        if (searchQuery.value.isNotBlank()) {
            if (!isSearchBarActive)
                searchQuery.value = ""
            else {
                onSearchDone(searchQuery.value)
            }
        } else {
            isSearchBarActive = false
        }
    }

    fun onSearchDone(query: String) {
        isSearchBarActive = false
        searchHistory.add(query)
    }

    fun onActiveChanged(isActive: Boolean) {
        isSearchBarActive = isActive
    }

    fun playCommand(title: String) {
        viewModelScope.launch {
            speakerManager.speak(title).collect {
                Log.d("ab_do", "Speech result ${it.name}")
            }
        }

    }

    fun onFavouriteClick(it: CommandDetails) {
        viewModelScope.launch {
            isAddToFavouriteToLoading = true
            commandsRepo.updateCommand(it.copy(isFavourite = !it.isFavourite))
            isAddToFavouriteToLoading = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        speakerManager.pause()
    }
}
