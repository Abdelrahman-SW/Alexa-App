package com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupDetails.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.ui.theme.lightBlue100

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPageIndicator(modifier: Modifier = Modifier , pagerState: PagerState) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) {
            val color = if (pagerState.currentPage == it) {
                lightBlue100
            } else {
                Color.Gray
            }
            Box(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}