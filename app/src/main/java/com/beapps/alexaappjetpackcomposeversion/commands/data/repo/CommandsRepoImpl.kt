package com.beapps.alexaappjetpackcomposeversion.commands.data.repo

import android.content.Context
import com.beapps.alexaappjetpackcomposeversion.R
import com.beapps.alexaappjetpackcomposeversion.commands.data.db.CommandDatabase
import com.beapps.alexaappjetpackcomposeversion.commands.data.toCommandDetails
import com.beapps.alexaappjetpackcomposeversion.commands.data.toCommandDetailsEntity
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandDetails
import com.beapps.alexaappjetpackcomposeversion.commands.domain.CommandsRepo
import com.beapps.alexaappjetpackcomposeversion.core.data.loadJSONData
import com.beapps.alexaappjetpackcomposeversion.core.domin.KeysStoreManagement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.json.JSONArray
import javax.inject.Inject

class CommandsRepoImpl @Inject constructor(
    private val context: Context,
    private val commandDatabase: CommandDatabase,
    private val keyStoreManagement : KeysStoreManagement
) : CommandsRepo {


    private val jsonStr by lazy { loadJSONData(context, context.getString(R.string.commands_path)) }
    override fun getAllFavouriteCommands(): Flow<List<CommandDetails>> {
        return commandDatabase.myDao().getAllFavouriteCommands().map { list ->
            list.map { it.toCommandDetails() }
        }
    }

    override fun getAllCommandsForSpecificCategory(commandCategory: CommandCategory?): Flow<List<CommandDetails>> {
        if (commandCategory == null) return emptyFlow()
        return if (commandCategory.title == context.getString(R.string.favourite)) {
            getAllFavouriteCommands()
        } else {
            commandDatabase.myDao().getAllCommandsForSpecificCategory(commandCategory.title)
                .map { list -> list.map { it.toCommandDetails() } }
        }
    }


    override suspend fun updateCommands(commandDetailItems: List<CommandDetails>) {
        commandDatabase.myDao().updateCommands(commandDetailItems.map { it.toCommandDetailsEntity() })
    }

    override suspend fun updateCommand(commandDetailItem: CommandDetails) {
        commandDatabase.myDao().updateCommand(commandDetailItem.toCommandDetailsEntity())
    }

    override fun isCommandsSavedInDb(): Boolean {
        return keyStoreManagement.getIfCommandsDbIsSavedState()
    }

    override fun commandsDbIsValid(): Boolean {
       return keyStoreManagement.getIfCommandsDbIsValidState()
    }

    override suspend fun searchCommands(query: String): List<CommandDetails> {
        return commandDatabase.myDao().getAllCommandsForSearchQuery(query)
            .map { it.toCommandDetails() }
    }


    override suspend fun getAllCommandsCategories(): List<CommandCategory> {
        return withContext(Dispatchers.IO) {
            if (!isCommandsSavedInDb()) {
                extractAndSaveAllCommandsInDb()
            }
            else {
                // check if it valid
                if (!commandsDbIsValid()) {
                    reUpdateCommandsTitle()
                    keyStoreManagement.saveIfCommandsDbIsValidState(true)
                }
            }
            var result: List<CommandCategory> = listOf()
            withContext(Dispatchers.Main) {
                jsonStr?.let {
                    val jsonArray = JSONArray(jsonStr)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val title = jsonObject.getString("title")
                        val contentJsonObject = jsonObject.getJSONArray("content").getJSONObject(0)
//                        val tagLine = contentJsonObject.getString("tag_line")
                        result = result + CommandCategory(title)
                    }
                    result
                } ?: listOf()
            }
        }
    }

    override suspend fun extractAndSaveAllCommandsInDb() {
        val commandDetailEntities = mutableListOf<CommandDetails>()
        val jsonArray = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val categoryType = jsonObject.getString("title")
            val contentArray = jsonObject.getJSONArray("content")

            for (x in 0 until contentArray.length()) {
                val content = contentArray.getJSONObject(x)
                //val tagLine = content.getString("tag_line")
                val detailsArray = content.getJSONArray("details")
                for (y in 0 until detailsArray.length()) {
                    val detailsObj = detailsArray.getJSONObject(y)
                    val commandTitle = detailsObj.getString("name")
                    val commandDetail = CommandDetails(title = commandTitle, categoryType = categoryType)
                    commandDetailEntities.add(commandDetail)
                }
            }
        }
        insertCommands(commandDetailEntities)
        updateCommandsSavedInDb(true)
        keyStoreManagement.saveIfCommandsDbIsValidState(true)

    }

    override suspend fun insertCommands(commands: List<CommandDetails>) {
        commandDatabase.myDao().insertCommands(commands.map { it.toCommandDetailsEntity() })
    }
    override fun updateCommandsSavedInDb(isSaved: Boolean) {
        keyStoreManagement.saveIfCommandsDbIsSavedState(isSaved)
    }

    override suspend fun reUpdateCommandsTitle() {
        val allCommands = commandDatabase.myDao().getAllCommands()
        var allCommandsIndex = 0
        val commandDetailEntities = mutableListOf<CommandDetails>()
        val jsonArray = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val categoryTitle = jsonObject.getString("title")
            val contentArray = jsonObject.getJSONArray("content")
            for (x in 0 until contentArray.length()) {
                val content = contentArray.getJSONObject(x)
                val detailsArray = content.getJSONArray("details")
                for (y in 0 until detailsArray.length()) {
                    val detailsObj = detailsArray.getJSONObject(y)
                    val commandTitle = detailsObj.getString("name")
                    val commandDetail = CommandDetails(id = allCommands[allCommandsIndex].id, title = commandTitle, categoryType = categoryTitle , isFavourite = allCommands[allCommandsIndex].isFavourite)
                    commandDetailEntities.add(commandDetail)
                    allCommandsIndex++
                }
            }
            updateCommands(commandDetailEntities)
        }
    }


}