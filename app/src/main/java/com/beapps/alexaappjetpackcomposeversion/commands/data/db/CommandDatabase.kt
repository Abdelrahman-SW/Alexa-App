package com.beapps.alexaappjetpackcomposeversion.commands.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beapps.alexaappjetpackcomposeversion.commands.data.models.CommandDetailsEntity

@Database(entities = [CommandDetailsEntity::class] , version = 1, exportSchema = false)
abstract class CommandDatabase : RoomDatabase() {

    abstract fun myDao(): CommandDao

    companion object {
        const val DATABASE_NAME = "commands_db"
    }

}