package com.beapps.alexaappjetpackcomposeversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.CommandsSharedViewModel
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.CommandsCategoryScreen
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails.CommandsDetailsScreen
import com.beapps.alexaappjetpackcomposeversion.core.presentation.Screen
import com.beapps.alexaappjetpackcomposeversion.core.presentation.ScreensWithBottomNavigationBar
import com.beapps.alexaappjetpackcomposeversion.core.presentation.bottomNavigationBarItems
import com.beapps.alexaappjetpackcomposeversion.core.presentation.components.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.settings.presentation.SettingsScreen
import com.beapps.alexaappjetpackcomposeversion.setupAndGroups.presentation.SetupAndGroupsScreen
import com.beapps.alexaappjetpackcomposeversion.translation.presentation.TranslationScreen
import com.beapps.alexaappjetpackcomposeversion.ui.theme.AlexaAppJetpackComposeVersionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlexaAppJetpackComposeVersionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val commandsSharedViewModel = hiltViewModel<CommandsSharedViewModel>()

                    val selectedIndex by remember {
                        derivedStateOf {
                            bottomNavigationBarItems.indexOfFirst {
                                it.route == (backStackEntry?.destination?.route
                                    ?: Screen.CommandsCategoryScreen.route)
                            }
                        }
                    }
                    Scaffold(
                        bottomBar = {
                            if ((backStackEntry?.destination?.route
                                    ?: Screen.CommandsCategoryScreen.route) in ScreensWithBottomNavigationBar
                            ) {
                                NavigationBar {
                                    bottomNavigationBarItems.forEachIndexed { index, item ->
                                        NavigationBarItem(
                                            selected = selectedIndex == index,
                                            onClick = {
                                                if ((backStackEntry?.destination?.route
                                                        ?: "null") != item.route
                                                )
                                                    navController.navigate(item.route)
                                            },
                                            icon = {
                                                Icon(
                                                    imageVector = if (selectedIndex == index) item.selectedIcon else item.unSelectedIcon,
                                                    contentDescription = item.title
                                                )
                                            },
                                            label = {
                                                Text(
                                                    text = item.title,
                                                    fontWeight = if (selectedIndex == index) FontWeight.ExtraBold else FontWeight.Normal,
                                                    fontFamily = poppinsFontFamily
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = Screen.CommandsCategoryScreen.route,
                            modifier = Modifier.padding(it)
                        ) {
                            composable(Screen.CommandsCategoryScreen.route) {
                                CommandsCategoryScreen(commandsSharedViewModel = commandsSharedViewModel , navController = navController)
                            }
                            composable(Screen.CommandsDetailsScreen.route) {
                                CommandsDetailsScreen(commandsSharedViewModel = commandsSharedViewModel)
                            }
                            composable(Screen.SetupAndGroupsScreen.route) {
                                SetupAndGroupsScreen()
                            }
                            composable(Screen.TranslationScreen.route) {
                                TranslationScreen()
                            }
                            composable(Screen.SettingsScreen.route) {
                                SettingsScreen()
                            }
                        }
                    }

                }
            }
        }
    }
}
