package com.beapps.alexaappjetpackcomposeversion.setup.domain.models

data class SetupItem(
    val title: String,
    val type : SetupType
)

enum class SetupType {
    MAIN_SETUP,
    GROUPS
}
