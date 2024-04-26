package com.beapps.alexaappjetpackcomposeversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.CommandsScreen
import com.beapps.alexaappjetpackcomposeversion.core.presentation.Screen
import com.beapps.alexaappjetpackcomposeversion.settings.presentation.SettingsScreen
import com.beapps.alexaappjetpackcomposeversion.setupAndGroups.presentation.SetupAndGroupsScreen
import com.beapps.alexaappjetpackcomposeversion.translation.presentation.TranslationScreen
import com.beapps.alexaappjetpackcomposeversion.ui.theme.AlexaAppJetpackComposeVersionTheme

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
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CommandsScreen.route
                    ) {
                        composable(Screen.CommandsScreen.route) {
                            CommandsScreen()
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
