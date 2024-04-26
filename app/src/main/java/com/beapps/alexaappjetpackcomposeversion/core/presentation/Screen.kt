package com.beapps.alexaappjetpackcomposeversion.core.presentation

sealed class Screen(val route: String) {
    data object CommandsScreen : Screen("commands")
    data object SetupAndGroupsScreen : Screen("setupAndGroups")
    data object TranslationScreen : Screen("translation")
    data object SettingsScreen : Screen("settings")
}
