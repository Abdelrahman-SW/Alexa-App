package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain

import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.SupportedLanguages
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TranslateResult
import kotlinx.coroutines.flow.Flow

interface Translator {
    fun translate(
        text: String,
        sourceLanguage: SupportedLanguages,
        targetLanguage: SupportedLanguages = SupportedLanguages.ENGLISH
    ): Flow<TranslateResult>

}