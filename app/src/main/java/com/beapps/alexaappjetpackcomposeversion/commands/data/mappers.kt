package com.beapps.alexaappjetpackcomposeversion.commands.data

import com.beapps.alexaappjetpackcomposeversion.commands.data.models.CommandDetailsEntity
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandDetails

fun CommandDetailsEntity.toCommandDetails(): CommandDetails {
    return CommandDetails(
        id,
        title,
        categoryType,
        isFavourite
    )
}


fun CommandDetails.toCommandDetailsEntity(): CommandDetailsEntity {
    return CommandDetailsEntity(
        id,
        title,
        categoryType,
        isFavourite
    )
}