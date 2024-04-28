package com.beapps.alexaappjetpackcomposeversion.commands.data.repo

import android.content.Context
import android.util.Log
import com.beapps.alexaappjetpackcomposeversion.commands.data.db.CommandDatabase
import com.beapps.alexaappjetpackcomposeversion.commands.data.getDrawableIdFromCategoryName
import com.beapps.alexaappjetpackcomposeversion.commands.data.toCommandDetails
import com.beapps.alexaappjetpackcomposeversion.commands.data.toCommandDetailsEntity
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandDetails
import com.beapps.alexaappjetpackcomposeversion.commands.domain.CommandsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class CommandsRepoImpl @Inject constructor(
    private val context: Context, private val commandDatabase: CommandDatabase
) : CommandsRepo {

    private val jsonStr by lazy { loadJSONData() }
    override fun getAllFavouriteCommands(): Flow<List<CommandDetails>> {
        return commandDatabase.myDao().getAllFavouriteCommands().map { list ->
            list.map { it.toCommandDetails() }
        }
    }

    override suspend fun getAllCommandsForSpecificCategory(categoryType: String): List<CommandDetails> {
        return commandDatabase.myDao().getAllCommandsForSpecificCategory(categoryType).map {
            it.toCommandDetails()
        }
    }

    override suspend fun updateCommand(commandDetailItem: CommandDetails) {
        commandDatabase.myDao().updateCommand(commandDetailItem.toCommandDetailsEntity())
    }

    private suspend fun insertCommands(commands: List<CommandDetails>) {
        commandDatabase.myDao().insertCommands(commands.map { it.toCommandDetailsEntity() })
    }

    override fun isCommandsSavedInDb(): Boolean {
        val sharedPref = context.getSharedPreferences("commands_db", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isCommandsSaved", false)
    }

    override suspend fun searchCommands(query: String): List<CommandDetails> {
        return commandDatabase.myDao().getAllCommandsForSearchQuery(query)
            .map { it.toCommandDetails() }
    }

    private fun updateCommandsSavedInDb(isSaved: Boolean) {
        val sharedPref = context.getSharedPreferences("commands_db", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("isCommandsSaved", isSaved).apply()
    }

    private fun loadJSONData(): String? {
        val filePath = "commands.json"
        val json: String = try {
            val inputStream: InputStream = context.assets.open(filePath)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            Log.d("ab_do", "loadJSONFromAsset: -->" + ex.message)
            return null
        }
        return json
    }

    override suspend fun getAllCommandsCategories(): List<CommandCategory> {
        return withContext(Dispatchers.IO) {
            var result: List<CommandCategory> = listOf()
            withContext(Dispatchers.Main) {
                jsonStr?.let {
                    val jsonArray = JSONArray(jsonStr)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val title = jsonObject.getString("title")
                        result =
                            result + CommandCategory(title, title.getDrawableIdFromCategoryName())
                    }
                    result
                } ?: listOf()
            }
        }
    }

    override suspend fun extractAndSaveAllCommandsInDb() {
        if (isCommandsSavedInDb()) return
        val commandDetailEntities = mutableListOf<CommandDetails>()
        val jsonArray = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val categoryType = jsonObject.getString("title")
            val contentArray = jsonObject.getJSONArray("content")
            for (x in 0 until contentArray.length()) {
                val content = contentArray.getJSONObject(x)
                val detailsArray = content.getJSONArray("details")
                for (y in 0 until detailsArray.length()) {
                    val detailsObj = detailsArray.getJSONObject(y)
                    val commandTitle = detailsObj.getString("name")
                    val commandDetail =
                        CommandDetails(title = commandTitle, categoryType = categoryType)
                    commandDetailEntities.add(commandDetail)
                }
            }
        }
        insertCommands(commandDetailEntities)
        updateCommandsSavedInDb(true)
    }
}