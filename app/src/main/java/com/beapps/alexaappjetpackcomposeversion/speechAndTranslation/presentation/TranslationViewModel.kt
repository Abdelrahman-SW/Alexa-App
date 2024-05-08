package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beapps.alexaappjetpackcomposeversion.core.domin.SpeakerManager
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.SpeechResult
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.SupportedLanguages
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TheSpeechRecognizer
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TranslateResult
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.Translator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val speakerManager: SpeakerManager,
    private val theSpeechRecognizer: TheSpeechRecognizer,
    private val translator: Translator
) : ViewModel() {

    var screenState: TranslationScreenState by mutableStateOf(TranslationScreenState())
        private set


    fun init() {
        theSpeechRecognizer.init()
        theSpeechRecognizer.setLanguage("ar")
        speakerManager.setup()
    }

    fun onSelectedLanguage(language: SupportedLanguages) {
        screenState = screenState.copy(selectedLanguage = language)
        theSpeechRecognizer.setLanguage(language.tag)
    }

    private fun startListening() {
        viewModelScope.launch {
            theSpeechRecognizer.startListening().collect { result ->
                when (result) {
                    SpeechResult.EndOfSpeech -> {
                        screenState =
                            screenState.copy(speechRecognizerState = SpeechRecognizerState.Ready)
                        cancel()
                    }

                    is SpeechResult.Error -> {
                        screenState = screenState.copy(
                            speechRecognizerResult = "",
                            speechRecognizerState = SpeechRecognizerState.Error(result.error)
                        )
                    }

                    is SpeechResult.FinalResult -> {
                        screenState = screenState.copy(speechRecognizerResult = result.result)
                    }

                    is SpeechResult.PartialResult -> {
                        screenState = screenState.copy(speechRecognizerResult = result.result)
                    }

                    SpeechResult.ReadyToListen -> {
                        screenState = screenState.copy(
                            speechRecognizerResult = "",
                            speechRecognizerState = SpeechRecognizerState.Listening
                        )
                    }
                }
            }
        }
    }

    fun onMicClicked() {
        when (screenState.speechRecognizerState) {
            is SpeechRecognizerState.Error -> startListening()
            SpeechRecognizerState.Listening -> stopListening()
            SpeechRecognizerState.Ready -> startListening()
        }
    }

    private fun stopListening() {
        theSpeechRecognizer.stopListening()
        screenState = screenState.copy(speechRecognizerState =  SpeechRecognizerState.Ready)
    }

    fun onTranslateBtnClicked() {
        screenState = screenState.copy(translationState = TranslationState.Idle)
        viewModelScope.launch {
            translator.translate(
                text = screenState.speechRecognizerResult,
                sourceLanguage = screenState.selectedLanguage!!,
            ).collect {
                screenState = when (it) {
                    is TranslateResult.Error -> {
                        screenState.copy(translationState = TranslationState.Error(it.error))
                    }

                    is TranslateResult.Success -> {
                        screenState.copy(translationState = TranslationState.Idle, translationResult = it.translatedText)
                    }
                }
            }
        }
    }

    fun onLanguageDialogDismiss() {
        screenState = screenState.copy(openLanguageDialog = false)
    }

    fun onLanguageBtnClicked() {
        screenState = screenState.copy(openLanguageDialog = true)
    }

    fun handleAudioPermResult(granted: Boolean) {
        screenState = screenState.copy(isRecordAudioPermGranted = granted)
    }


}