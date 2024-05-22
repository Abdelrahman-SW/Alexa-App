package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.commands.presentation.getDrawableIdFromCategoryTitle
import com.beapps.alexaappjetpackcomposeversion.ui.theme.lightBlue100

@Composable
fun CategoryIcon(modifier: Modifier = Modifier , item: CommandCategory) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(20.dp))
        .background(lightBlue100)
        .padding(vertical = 16.dp, horizontal = 28.dp),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            painter = painterResource(id = item.title.getDrawableIdFromCategoryTitle()),
            contentDescription = item.title,
            modifier = modifier.size(28.dp),
            tint = Color.White
        )
    }
}
