package com.beapps.alexaappjetpackcomposeversion.setup.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.components.SetupHeader
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.components.SetupItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SetupScreen(modifier: Modifier = Modifier) {
    val viewModel: SetupViewModel = hiltViewModel()
    val setupGroupItems by viewModel.setupGroupsItems.collectAsState(listOf())
    val setupMainSetupItems by viewModel.setupMainSetupItems.collectAsState(listOf())

    if (viewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            stickyHeader {
                SetupHeader(title = "Main Setup")
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(setupMainSetupItems) {
                SetupItem(item = it) {

                }
            }
            stickyHeader {
                SetupHeader(title = "Groups")
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(setupGroupItems) {
                SetupItem(item = it) {

                }
            }

        }
    }
}