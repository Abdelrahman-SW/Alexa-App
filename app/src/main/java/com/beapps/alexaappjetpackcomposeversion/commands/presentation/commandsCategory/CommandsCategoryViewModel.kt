package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.getDrawableIdFromCategoryName
import com.beapps.alexaappjetpackcomposeversion.core.presentation.loadJSONFromAsset
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class CommandsCategoryViewModel @Inject constructor(
    private val application: Application
) : AndroidViewModel(application) {

    var commandCategories by mutableStateOf(listOf<CommandCategory>())

    init {
        getAllCommandsCategories()
    }

    private fun getAllCommandsCategories() {
        var result: List<CommandCategory> = listOf()
        val jsonStr = loadJSONFromAsset(application.applicationContext, "commands.json")
        commandCategories = jsonStr?.let {
            val jsonArray = JSONArray(jsonStr)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val title = jsonObject.getString("title")
                result = result + CommandCategory(title, title.getDrawableIdFromCategoryName())
            }
            result
        } ?: listOf()
    }
}