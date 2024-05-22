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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.beapps.alexaappjetpackcomposeversion.R

data class NavigationBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route : String
)

val ScreensWithBottomNavigationBar = listOf(
    Screen.CommandsCategoryScreen.route,
    Screen.SetupAndGroupsScreen.route,
    Screen.TranslationScreen.route,
    Screen.SettingsScreen.route,
)

@Composable
fun getBottomNavigationBarItems(): List<NavigationBarItem> {
    return listOf(
        NavigationBarItem(
            stringResource(R.string.commands) ,
            Icons.Filled.KeyboardCommandKey ,
            Icons.Outlined.KeyboardCommandKey,
            Screen.CommandsCategoryScreen.route
        ),
        NavigationBarItem(
            stringResource(R.string.setup) ,
            Icons.Filled.WifiProtectedSetup ,
            Icons.Outlined.WifiProtectedSetup,
            Screen.SetupAndGroupsScreen.route
        ),
        NavigationBarItem(
            stringResource(R.string.translation) ,
            Icons.Filled.Translate ,
            Icons.Outlined.Translate,
            Screen.TranslationScreen.route
        ),
        NavigationBarItem(
            stringResource(R.string.settings) ,
            Icons.Filled.Settings ,
            Icons.Outlined.Settings,
            Screen.SettingsScreen.route
        ),
    )
}
