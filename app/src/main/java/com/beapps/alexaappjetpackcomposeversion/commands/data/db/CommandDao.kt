package com.beapps.alexaappjetpackcomposeversion.commands.data.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.beapps.alexaappjetpackcomposeversion.commands.data.models.CommandDetailsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CommandDao {
    @Query ("select * from CommandDetailsEntity where isFavourite = 1")
    fun getAllFavouriteCommands() : Flow<List<CommandDetailsEntity>>

    @Query ("select * from CommandDetailsEntity where categoryType = :categoryType")
    fun getAllCommandsForSpecificCategory(categoryType : String) : Flow<List<CommandDetailsEntity>>

    @Query ("select * from CommandDetailsEntity where title Like '%' || :query || '%'")
    suspend fun getAllCommandsForSearchQuery(query : String) : List<CommandDetailsEntity>

    @Update
    suspend fun updateCommand (commandDetailItem: CommandDetailsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommands (commands : List<CommandDetailsEntity>)
}