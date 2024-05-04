package com.beapps.alexaappjetpackcomposeversion.core.presentation

sealed class Screen(val route: String) {
    data object CommandsCategoryScreen : Screen("commands-category")
    data object CommandsDetailsScreen : Screen("commands-details")
    data object SetupAndGroupsScreen : Screen("setupAndGroups")
    data object SetupAndGroupsScreenDetails : Screen("setupAndGroupsDetails")
    data object TranslationScreen : Screen("translation")
    data object SettingsScreen : Screen("settings")
}
