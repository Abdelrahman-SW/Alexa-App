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
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.QuestionMark
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
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.CommandsViewModel
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components.CommandCategoryItem
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails.CommandsDetailsScreen
import com.beapps.alexaappjetpackcomposeversion.core.presentation.Screen
import com.beapps.alexaappjetpackcomposeversion.core.presentation.components.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommandsCategoryScreen(
    modifier: Modifier = Modifier,
    commandsViewModel: CommandsViewModel,
    navController: NavController
) {

    val commandCategories = commandsViewModel.commandCategories
    val searchQuery by commandsViewModel.searchQuery.collectAsState()
    val isSearchingActive by commandsViewModel.isSearchingActive.collectAsState()
    val isSearchBarActive = commandsViewModel.isSearchBarActive
    val searchHistory = commandsViewModel.searchHistory
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
    if (commandsViewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            Modifier.fillMaxSize()
        ) { padding ->
            Column(modifier.padding(padding)) {

                Spacer(modifier = Modifier.height(8.dp))

                SearchBar(modifier = Modifier.align(Alignment.CenterHorizontally),
                    query = searchQuery,
                    onQueryChange = {
                        commandsViewModel.onSearchQueryChanged(it)
                    },
                    onSearch = {
                        commandsViewModel.onSearchDone(it)
                    },
                    active = commandsViewModel.isSearchBarActive,
                    onActiveChange = { commandsViewModel.onActiveChanged(it) },
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
                                    commandsViewModel.onSearchTrailingIconClicked()
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
                                    commandsViewModel.onActiveChanged(false)
                                    commandsViewModel.onSearchQueryChanged(search)
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
                    CommandsDetailsScreen(commandsViewModel = commandsViewModel)
                } else {
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
                                commandsViewModel.onSelectedCategory(it.title)
                            }
                        }
                    }

                }
            }

        }
    }
}