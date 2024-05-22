package com.beapps.alexaappjetpackcomposeversion.commands.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.beapps.alexaappjetpackcomposeversion.R

@Composable
fun String.getDrawableIdFromCategoryTitle () : Int {
    return when  {
        equals(stringResource(id = R.string.basic) , ignoreCase = true) -> R.drawable.basic
        else -> R.drawable.notifications
    }
}
