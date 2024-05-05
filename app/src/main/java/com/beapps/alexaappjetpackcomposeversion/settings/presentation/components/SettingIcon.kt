package com.beapps.alexaappjetpackcomposeversion.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.settings.domain.SettingItem
import com.beapps.alexaappjetpackcomposeversion.ui.theme.lightBlue100

@Composable
fun SettingIcon(modifier: Modifier = Modifier, settingItem: SettingItem) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(lightBlue100)
            .padding(vertical = 10.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            imageVector = settingItem.icon,
            contentDescription = settingItem.title,
            modifier = modifier.size(22.dp),
            tint = Color.White
        )
    }
}