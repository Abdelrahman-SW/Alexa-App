package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation


import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.SpeechErrors
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.SupportedLanguages
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TranslationErrors

data class TranslationScreenState(
    val translationResults : List<String> = emptyList(),
    val openLanguageDialog : Boolean = false,
    val isRecordAudioPermGranted : Boolean = false,
    val speechRecognizerResult: String = "",
    val selectedLanguage : SupportedLanguages? = null,
    val speechRecognizerState: SpeechRecognizerState = SpeechRecognizerState.Ready,
    val translationState: TranslationState = TranslationState.Ready
)


sealed class SpeechRecognizerState {
    data object Ready : SpeechRecognizerState()
    data object Listening : SpeechRecognizerState()
    data class Error(val error: SpeechErrors) : SpeechRecognizerState()

}

sealed class TranslationState {
    data object Ready : TranslationState()
    data object Translating : TranslationState()
    data class Error (val error: TranslationErrors) : TranslationState()

}
