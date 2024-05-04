package com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupDetails.components

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.R
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components.CategoryIcon
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupDetailItem
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor35Alpha

@Composable
fun SetupDetailPage(modifier: Modifier = Modifier, setupDetailItem: SetupDetailItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = mainComponentColor35Alpha),
        modifier = modifier.padding(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            setupDetailItem.imageId?.let { id ->
                Image(painter = painterResource(id = id), contentDescription = "image" , modifier = Modifier.weight(3f))
                Spacer(modifier = Modifier.height(16.dp))
            }
            setupDetailItem.content?.let {
                Column(modifier = Modifier.verticalScroll(rememberScrollState()).weight(1f)) {
                    Text(
                        text = it,
                        fontFamily = poppinsFontFamily,
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        //fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}