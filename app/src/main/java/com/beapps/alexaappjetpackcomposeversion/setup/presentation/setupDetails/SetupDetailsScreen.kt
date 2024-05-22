package com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupDetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.beapps.alexaappjetpackcomposeversion.R
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.SetupSharedViewModel
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupDetails.components.HorizontalPageIndicator
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupDetails.components.SetupDetailPage
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SetupDetailsScreen(modifier: Modifier = Modifier, viewModel: SetupSharedViewModel , navController: NavController) {
    val selectedSetupItemDetail by viewModel.selectedSetupItemDetail.collectAsState(initial = null)
    val selectedSetupItem by viewModel.selectedSetupItem.collectAsState(initial = null)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = selectedSetupItem?.title ?: stringResource(R.string.none),
                        color = Color.White,
                        fontFamily = poppinsFontFamily,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = mainComponentColor),
                scrollBehavior = scrollBehavior
            )
        }
    )
    { padding ->
        if (viewModel.isLoading) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            selectedSetupItemDetail?.let {
                val pagerState = rememberPagerState(
                    initialPage = 0,
                    pageCount = { it.size })
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPager(
                        modifier = Modifier.weight(1f),
                        state = pagerState
                    ) { pageIndex ->
                        SetupDetailPage(setupDetailItem = it[pageIndex])
                    }

                    HorizontalPageIndicator(
                        pagerState = pagerState,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

            }

        }
    }
}
