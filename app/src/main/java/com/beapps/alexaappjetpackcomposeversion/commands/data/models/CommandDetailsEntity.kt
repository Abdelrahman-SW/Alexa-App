package com.beapps.alexaappjetpackcomposeversion.commands.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommandDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val categoryType: String,
    val isFavourite: Boolean = false
)


