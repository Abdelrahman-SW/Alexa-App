package com.beapps.alexaappjetpackcomposeversion.commands.domain.models

data class CommandDetails(
    val id : Int = 0,
    val title : String ,
    val categoryType: String,
    val isFavourite : Boolean = false,
)
