package com.beapps.alexaappjetpackcomposeversion.commands.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.beapps.alexaappjetpackcomposeversion.R

fun String.getDrawableIdFromCategoryName () : Int {
    return when  {
        contains("Basic") -> R.drawable.basic
        else -> R.drawable.notifications
    }
}