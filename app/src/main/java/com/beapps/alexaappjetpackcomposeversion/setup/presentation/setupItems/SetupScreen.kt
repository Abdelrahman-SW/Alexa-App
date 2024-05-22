package com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupItems

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.beapps.alexaappjetpackcomposeversion.R
import com.beapps.alexaappjetpackcomposeversion.core.presentation.Screen
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.SetupSharedViewModel
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupItems.components.SetupHeader
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupItems.components.SetupItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SetupScreen(modifier: Modifier = Modifier , viewModel: SetupSharedViewModel , navController: NavController) {
    val setupGroupItems by viewModel.setupGroupsItems.collectAsState(listOf())
    val setupMainSetupItems by viewModel.setupMainSetupItems.collectAsState(listOf())

    if (viewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            stickyHeader {
                SetupHeader(title = stringResource(R.string.main_setup))
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(setupMainSetupItems , key = {it.title}) {
                SetupItem(item = it){item ->
                    viewModel.onSetupItemClick(item)
                    navController.navigate(Screen.SetupAndGroupsScreenDetails.route)
                }
            }
            stickyHeader {
                SetupHeader(title = stringResource(R.string.groups))
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(setupGroupItems) {
                SetupItem(item = it){ item ->
                    viewModel.onSetupItemClick(item)
                    navController.navigate(Screen.SetupAndGroupsScreenDetails.route)
                }
            }

        }
    }
}