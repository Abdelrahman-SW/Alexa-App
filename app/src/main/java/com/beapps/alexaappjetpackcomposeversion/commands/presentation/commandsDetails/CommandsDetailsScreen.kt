package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.beapps.alexaappjetpackcomposeversion.R
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.CommandsSharedViewModel
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails.components.CommandDetailItem
import com.beapps.alexaappjetpackcomposeversion.core.domin.shareText
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CommandsDetailsScreen(
    modifier: Modifier = Modifier,
    commandsSharedViewModel: CommandsSharedViewModel,
    navController: NavController
) {
    val commandsDetails by commandsSharedViewModel.commandDetails.collectAsState()
    val currentCategory by commandsSharedViewModel.currentCategory.collectAsState()
    val searchedCommands by commandsSharedViewModel.searchedCommands.collectAsState()
    val isSearchingActive by commandsSharedViewModel.isSearchingActive.collectAsState()
    val isAddToFavouriteToLoading = commandsSharedViewModel.isAddToFavouriteToLoading

    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()


    Scaffold(modifier = Modifier
        .fillMaxSize()
        .then(if (!isSearchingActive) modifier.nestedScroll(scrollBehavior.nestedScrollConnection) else modifier),
        topBar = {
            if (!isSearchingActive) {
                MediumTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = mainComponentColor),
                    title = {
                        Text(
                            text = currentCategory?.title ?: stringResource(id = R.string.none),
                            color = Color.White,
                            fontFamily = poppinsFontFamily,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        }) { padding ->
        if (commandsSharedViewModel.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(if (isSearchingActive) searchedCommands else commandsDetails,
                    key = { it.id }) { commandItem ->
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

}