package com.beapps.alexaappjetpackcomposeversion.setup.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.beapps.alexaappjetpackcomposeversion.R

@Composable
fun String.getDrawableIdFromSetupTitle(): Int {
    return when {
        equals(stringResource(R.string.echo), ignoreCase = true) -> R.drawable.basic
        else -> R.drawable.notifications
    }
}