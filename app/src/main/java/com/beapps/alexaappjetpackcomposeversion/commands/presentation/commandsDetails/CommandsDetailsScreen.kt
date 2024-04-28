package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
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
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.CommandsSharedViewModel
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails.components.CommandDetailItem
import com.beapps.alexaappjetpackcomposeversion.core.domin.shareText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CommandsDetailsScreen(modifier: Modifier = Modifier, commandsSharedViewModel: CommandsSharedViewModel) {
    val commandsDetails by commandsSharedViewModel.commandDetails.collectAsState()
    val searchedCommands by commandsSharedViewModel.searchedCommands.collectAsState()
    val isSearchingActive by commandsSharedViewModel.isSearchingActive.collectAsState()
    val isAddToFavouriteToLoading = commandsSharedViewModel.isAddToFavouriteToLoading

    val context = LocalContext.current
    if (commandsSharedViewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(if (isSearchingActive) searchedCommands else commandsDetails , key = {it.id}) { commandItem ->
                CommandDetailItem(
                    modifier = Modifier.animateItemPlacement(),
                    item = commandItem,
                    onItemClick = {
                        commandsSharedViewModel.playCommand(it.title)
                    },
                    onFavouriteClick = { commandsSharedViewModel.onFavouriteClick(it) },
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