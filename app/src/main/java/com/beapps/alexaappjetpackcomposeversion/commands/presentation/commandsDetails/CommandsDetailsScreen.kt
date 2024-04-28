package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.CommandsViewModel
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components.CommandCategoryItem
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails.components.CommandDetailItem
import com.beapps.alexaappjetpackcomposeversion.core.domin.shareText
import com.beapps.alexaappjetpackcomposeversion.core.presentation.Screen

@Composable
fun CommandsDetailsScreen(modifier: Modifier = Modifier, commandsViewModel: CommandsViewModel) {
    val commandsDetails by commandsViewModel.commandDetails.collectAsState()
    val searchedCommands by commandsViewModel.searchedCommands.collectAsState()
    val isSearchingActive by commandsViewModel.isSearchingActive.collectAsState()
    val isAddToFavouriteToLoading = commandsViewModel.isAddToFavouriteToLoading

    val context = LocalContext.current
    if (commandsViewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(if (isSearchingActive) searchedCommands else commandsDetails) { commandItem ->
                CommandDetailItem(
                    item = commandItem,
                    onItemClick = {
                        commandsViewModel.playCommand(it.title)
                    },
                    onFavouriteClick = { commandsViewModel.onFavouriteClick(it) },
                    onShareClick = { context.shareText(it.title) },
                )
                Divider()
            }
        }

        if (isAddToFavouriteToLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}