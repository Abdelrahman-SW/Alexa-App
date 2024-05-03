package com.beapps.alexaappjetpackcomposeversion.setup.domain.models

import androidx.annotation.DrawableRes

data class SetupItem(
    val title: String,
    @DrawableRes val iconId: Int,
    val type : SetupType
)

enum class SetupType {
    MAIN_SETUP,
    GROUPS
}
