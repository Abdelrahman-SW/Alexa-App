package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.CommandsSharedViewModel
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components.CommandCategoryItem
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails.CommandsDetailsScreen
import com.beapps.alexaappjetpackcomposeversion.core.presentation.Screen
import com.beapps.alexaappjetpackcomposeversion.core.presentation.components.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommandsCategoryScreen(
    modifier: Modifier = Modifier,
    commandsSharedViewModel: CommandsSharedViewModel,
    navController: NavController
) {

    val commandCategories = commandsSharedViewModel.commandCategories
    val searchQuery by commandsSharedViewModel.searchQuery.collectAsState()
    val isSearchingActive by commandsSharedViewModel.isSearchingActive.collectAsState()
    val isSearchBarActive = commandsSharedViewModel.isSearchBarActive
    val searchHistory = commandsSharedViewModel.searchHistory


    val searchBarTrailingIcon: ImageVector? = remember(searchQuery, isSearchBarActive) {
        if (isSearchBarActive && searchQuery.isBlank()) {
            Icons.Default.Close
        } else if (isSearchBarActive && searchQuery.isNotBlank()) {
            Icons.Default.Check
        } else if (!isSearchBarActive && searchQuery.isBlank()) {
            null
        } else {
            Icons.Default.Close
        }
    }

    if (commandsSharedViewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    else {
        Scaffold(
            Modifier.fillMaxSize()
        ) { padding ->
            Column(modifier.padding(padding)) {

                Spacer(modifier = Modifier.height(8.dp))

                SearchBar(modifier = Modifier.align(Alignment.CenterHorizontally),
                    query = searchQuery,
                    onQueryChange = {
                        commandsSharedViewModel.onSearchQueryChanged(it)
                    },
                    onSearch = {
                        commandsSharedViewModel.onSearchDone(it)
                    },
                    active = commandsSharedViewModel.isSearchBarActive,
                    onActiveChange = { commandsSharedViewModel.onActiveChanged(it) },
                    placeholder = {
                        Text(
                            text = "Search For Commands .. ", fontFamily = poppinsFontFamily
                        )
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    },
                    trailingIcon = {
                        searchBarTrailingIcon?.let {
                            Icon(imageVector = it,
                                contentDescription = "searchBarTrailingIcon",
                                modifier = Modifier.clickable {
                                    commandsSharedViewModel.onSearchTrailingIconClicked()
                                })
                        }


                    }) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                    ) {
                        searchHistory.reversed().forEach { search ->
                            Row(modifier = Modifier.fillMaxWidth()
                                .clickable {
                                    commandsSharedViewModel.onActiveChanged(false)
                                    commandsSharedViewModel.onSearchQueryChanged(search)
                                }
                                .padding(16.dp)) {
                                Icon(
                                    modifier = Modifier.padding(end = 16.dp),
                                    imageVector = Icons.Default.History,
                                    contentDescription = "History"
                                )
                                Text(text = search, fontFamily = poppinsFontFamily)
                            }
                        }
                    }
                }

                if (isSearchingActive) {
                    CommandsDetailsScreen(commandsSharedViewModel = commandsSharedViewModel)
                }
                else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = modifier.fillMaxSize().padding(top = 16.dp),
                        contentPadding = PaddingValues(bottom = 16.dp , start = 16.dp , end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        items(commandCategories) { category ->
                            CommandCategoryItem(item = category) {
                                navController.navigate(Screen.CommandsDetailsScreen.route)
                                commandsSharedViewModel.onSelectedCategory(it.title)
                            }
                        }
                    }

                }
            }

        }
    }
}