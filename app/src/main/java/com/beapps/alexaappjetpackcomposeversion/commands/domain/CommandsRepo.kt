package com.beapps.alexaappjetpackcomposeversion.commands.domain

import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandDetails
import kotlinx.coroutines.flow.Flow

interface CommandsRepo {
    fun getAllFavouriteCommands() : Flow<List<CommandDetails>>

    suspend fun getAllCommandsForSpecificCategory(categoryType : String) : List<CommandDetails>

    suspend fun updateCommand (commandDetailItem: CommandDetails)

    suspend fun getAllCommandsCategories() : List<CommandCategory>
    suspend fun extractAndSaveAllCommandsInDb()

    fun isCommandsSavedInDb(): Boolean

    suspend fun searchCommands (query : String) : List<CommandDetails>

}