package com.beapps.alexaappjetpackcomposeversion.translation.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TranslationScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp) , contentAlignment = Alignment.Center) {
        Text(
            text = "Translation",
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
    }
}