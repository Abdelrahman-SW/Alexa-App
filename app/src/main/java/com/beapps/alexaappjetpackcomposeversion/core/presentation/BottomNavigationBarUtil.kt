package com.beapps.alexaappjetpackcomposeversion.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardCommandKey
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.WifiProtectedSetup
import androidx.compose.material.icons.outlined.KeyboardCommandKey
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material.icons.outlined.WifiProtectedSetup
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route : String
)

val ScreensWithBottomNavigationBar = listOf(
    Screen.CommandsScreen.route,
    Screen.SetupAndGroupsScreen.route,
    Screen.TranslationScreen.route,
    Screen.SettingsScreen.route,
)

val bottomNavigationBarItems = listOf(
   NavigationBarItem(
       "Commands" ,
       Icons.Filled.KeyboardCommandKey ,
       Icons.Outlined.KeyboardCommandKey,
       Screen.CommandsScreen.route
   ),
    NavigationBarItem(
        "Setup" ,
        Icons.Filled.WifiProtectedSetup ,
        Icons.Outlined.WifiProtectedSetup,
        Screen.SetupAndGroupsScreen.route
    ),
    NavigationBarItem(
        "Translation" ,
        Icons.Filled.Translate ,
        Icons.Outlined.Translate,
        Screen.TranslationScreen.route
    ),
    NavigationBarItem(
        "Settings" ,
        Icons.Filled.Settings ,
        Icons.Outlined.Settings,
        Screen.SettingsScreen.route
    ),
)