package com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupItems.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor

@Composable
fun SetupHeader(modifier: Modifier = Modifier , title : String) {
     Text(
         text = title,
         fontWeight = FontWeight.Bold,
         fontSize = 15.sp,
         fontFamily = poppinsFontFamily,
         modifier = modifier.fillMaxWidth().background(mainComponentColor).padding(horizontal = 12.dp , vertical = 12.dp)
     )
}