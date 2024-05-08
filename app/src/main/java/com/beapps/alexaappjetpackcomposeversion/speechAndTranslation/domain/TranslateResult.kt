package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain

sealed class TranslateResult {
    data class Success(val translatedText: String) : TranslateResult()
    data class Error(val error: TranslationErrors) : TranslateResult()
}

sealed class TranslationErrors {
    data object ModelNotFound : TranslationErrors()
    data object EmptyResult : TranslationErrors()
    data class Others(val e : Exception) : TranslationErrors()
}