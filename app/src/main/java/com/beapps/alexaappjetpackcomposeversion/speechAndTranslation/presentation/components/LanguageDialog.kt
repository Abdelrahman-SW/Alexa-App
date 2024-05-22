package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.SupportedLanguages
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor

@Composable
fun LanguageDialog(
    modifier: Modifier = Modifier,
    languages: Array<SupportedLanguages>,
    onItemClick: (SupportedLanguages) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = modifier
                .background(mainComponentColor)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            languages.forEachIndexed { index, supportedLanguages ->
                LanguageItem(
                    language = supportedLanguages,
                    onItemClick = onItemClick
                )
            }
        }
    }
}