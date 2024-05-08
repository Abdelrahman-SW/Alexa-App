package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.SupportedLanguages

@Composable
fun LanguageItem(
    modifier: Modifier = Modifier,
    onItemClick: (SupportedLanguages) -> Unit,
    language: SupportedLanguages
) {
    Text(text = language.name,
        textAlign = TextAlign.Center,
        color = Color.White,
        fontFamily = poppinsFontFamily,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(language)
            }
    )
}