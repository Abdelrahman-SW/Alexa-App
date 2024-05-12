package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain

import kotlinx.coroutines.flow.Flow

interface Translator {
    fun translate(
        text: String,
        sourceLanguage: SupportedLanguages,
        targetLanguage: SupportedLanguages = SupportedLanguages.ENGLISH
    ): Flow<TranslateResult>

}