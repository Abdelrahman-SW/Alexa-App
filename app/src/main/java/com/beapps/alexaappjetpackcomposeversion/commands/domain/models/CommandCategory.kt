package com.beapps.alexaappjetpackcomposeversion.commands.domain.models

import androidx.annotation.DrawableRes

data class CommandCategory(
    val title: String,
    @DrawableRes val iconId: Int
)