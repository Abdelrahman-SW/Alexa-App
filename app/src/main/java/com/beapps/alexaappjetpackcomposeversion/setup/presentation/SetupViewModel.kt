package com.beapps.alexaappjetpackcomposeversion.setup.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.beapps.alexaappjetpackcomposeversion.setup.domain.SetupRepo
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupItem
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    repo: SetupRepo
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    private val setupItems = MutableStateFlow<List<SetupItem>>(listOf())

    var setupGroupsItems = setupItems.map {it->
        it.filter { it.type == SetupType.GROUPS }
    }
    var setupMainSetupItems = setupItems.map {it->
        it.filter { it.type == SetupType.MAIN_SETUP }
    }

    init {
        isLoading = true
        setupItems.value = repo.getSetupData()
        isLoading = false
    }

}