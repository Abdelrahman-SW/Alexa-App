package com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupItems.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.core.presentation.randomColor
import com.beapps.alexaappjetpackcomposeversion.core.presentation.randomGradientColors
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupItem
import com.beapps.alexaappjetpackcomposeversion.setup.presentation.getDrawableIdFromSetupTitle

@Composable
fun SetupIcon(modifier: Modifier = Modifier, item: SetupItem) {

    //val colors = remember { randomGradientColors() }

    val color = remember { randomColor() }


    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            //.background(Brush.horizontalGradient(colors))
            .background(color)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            painter = painterResource(id = item.title.getDrawableIdFromSetupTitle()),
            contentDescription = item.title,
            modifier = modifier.size(16.dp),
            tint = Color.White
        )
    }
}