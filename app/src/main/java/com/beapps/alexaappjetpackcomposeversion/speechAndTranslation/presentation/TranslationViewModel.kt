package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation

import android.util.Log
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val speakerManager: SpeakerManager,
    private val theSpeechRecognizer: TheSpeechRecognizer,
    private val translator: Translator
) : ViewModel() {


    private var currentTranslationJob: Job? = null
    private var currentSpeechRecognizerJob: Job? = null
    var screenState: TranslationScreenState by mutableStateOf(TranslationScreenState())
        private set


    fun init() {
        theSpeechRecognizer.init()
        speakerManager.setup()
    }

    fun onSelectedLanguage(language: SupportedLanguages) {
        screenState = screenState.copy(
            selectedLanguage = language,
            speechRecognizerResult = "",
        )
        theSpeechRecognizer.setLanguage(language.tag)
    }

    private fun startListening() {
        currentSpeechRecognizerJob?.let {
            if (!it.isCancelled) it.cancel()
        }
        currentSpeechRecognizerJob = viewModelScope.launch {
            theSpeechRecognizer.startListening().collect { result ->
                Log.d("SpeechRecognizer", "result: $result")
                when (result) {
                    SpeechResult.EndOfSpeech -> {
                        screenState = screenState.copy(speechRecognizerState = SpeechRecognizerState.Ready)
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
        screenState = screenState.copy(speechRecognizerState = SpeechRecognizerState.Ready)
    }

    fun onTranslateBtnClicked() {
        screenState = screenState.copy(translationState = TranslationState.Translating)
        currentTranslationJob?.let {
            if (!it.isCancelled) it.cancel()
        }
        currentTranslationJob = viewModelScope.launch {
            translator.translate(
                text = screenState.speechRecognizerResult,
                sourceLanguage = screenState.selectedLanguage!!,
            ).collect { it ->
                screenState = when (it) {
                    is TranslateResult.Error -> {
                        screenState.copy(translationState = TranslationState.Error(it.error))
                    }

                    is TranslateResult.Success -> {
                        screenState.copy(
                            translationState = TranslationState.Ready,
                            speechRecognizerResult = "",
                            translationResults = screenState.translationResults + it.translatedText
                        )
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

    fun onTranslateResultItemClicked(translateResult : String) {
        viewModelScope.launch {
         speakerManager.speak(translateResult).collect {
             Log.d("SpeakerManager", "speak result: $it")
         }
        }
    }

    override fun onCleared() {
        super.onCleared()
        speakerManager.pause()
    }


}