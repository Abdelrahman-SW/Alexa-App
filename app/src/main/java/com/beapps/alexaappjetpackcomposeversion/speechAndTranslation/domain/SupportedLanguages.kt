package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.beapps.alexaappjetpackcomposeversion.R

enum class SupportedLanguages(val tag: String) {
    ENGLISH("en"),
    ARABIC("ar"),
    FRENCH("fr"),
    GERMAN("de"),
    INDONESIAN("id"),
    JAPANESE("ja"),
    PORTUGUESE("pt"),
    RUSSIAN("ru"),
    CHINESE("zh-CN"),
    SPANISH("es");

    @Composable
    fun getLanguageDisplayTitle(): String {
        val stringArray = LocalContext.current.resources.getStringArray(R.array.languages)
        return stringArray.map {
            it
        }[this.ordinal]
    }
}

val supportedLanguagesList = SupportedLanguages.entries.toTypedArray()
