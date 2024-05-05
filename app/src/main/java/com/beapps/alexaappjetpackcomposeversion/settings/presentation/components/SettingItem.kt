package com.beapps.alexaappjetpackcomposeversion.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components.CategoryIcon
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.settings.domain.SettingItem
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor35Alpha

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    settingItem: SettingItem,
    onClick: (item: SettingItem) -> Unit
) {
    Card(
        modifier = modifier
            .height(130.dp)
            .defaultMinSize(minWidth = 150.dp)
            .clickable { onClick(settingItem) }
            .padding(6.dp),
        colors = CardDefaults.cardColors(containerColor = mainComponentColor35Alpha)
    ) {
        Column(
            modifier = modifier.defaultMinSize(minWidth = 150.dp)
                .padding(vertical = 16.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingIcon(settingItem = settingItem)
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = settingItem.title,
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}