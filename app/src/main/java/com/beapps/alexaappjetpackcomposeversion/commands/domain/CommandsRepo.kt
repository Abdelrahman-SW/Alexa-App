package com.beapps.alexaappjetpackcomposeversion.commands.domain

import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandDetails
import kotlinx.coroutines.flow.Flow

interface CommandsRepo {
    fun getAllFavouriteCommands() : Flow<List<CommandDetails>>
    fun getAllCommandsForSpecificCategory(commandCategory : CommandCategory?) : Flow<List<CommandDetails>>
    suspend fun updateCommands (commandDetailItems: List<CommandDetails>)
    suspend fun updateCommand (commandDetailItem: CommandDetails)
    suspend fun insertCommands(commands: List<CommandDetails>)
    suspend fun searchCommands (query : String) : List<CommandDetails>
    suspend fun getAllCommandsCategories() : List<CommandCategory>
    suspend fun extractAndSaveAllCommandsInDb()
    fun isCommandsSavedInDb(): Boolean
    fun commandsDbIsValid(): Boolean
    fun updateCommandsSavedInDb(isSaved: Boolean)
    suspend fun reUpdateCommandsTitle()




}