package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class TheSpeechRecognizerImpl(private val context: Context) : TheSpeechRecognizer {

    private var speechRecognizer: SpeechRecognizer? = null
    private var speechRecognizerIntent: Intent? = null
    override fun init() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent!!.apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        }

    }

    override fun startListening(): Flow<SpeechResult> {
        return callbackFlow {

            speechRecognizer?.setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {
                    trySend(SpeechResult.ReadyToListen)
                }

                override fun onBeginningOfSpeech() {

                }

                override fun onRmsChanged(rmsdB: Float) {

                }

                override fun onBufferReceived(buffer: ByteArray?) {

                }

                override fun onEndOfSpeech() {
                    trySend(SpeechResult.EndOfSpeech)
                }

                override fun onError(error: Int) {
                    when (error) {
                        SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> {
                            trySend(SpeechResult.Error(SpeechErrors.BUSY))
                        }

                        SpeechRecognizer.ERROR_NETWORK -> {
                            trySend(SpeechResult.Error(SpeechErrors.NETWORK_ERROR))
                            close()

                        }

                        SpeechRecognizer.ERROR_CLIENT -> {
                            trySend(SpeechResult.Error(SpeechErrors.NETWORK_ERROR))
                            close()
                        }

                        SpeechRecognizer.ERROR_NO_MATCH -> {
                            trySend(SpeechResult.Error(SpeechErrors.EMPTY_RESULT))
                            close()
                        }

                        else -> {
                            trySend(SpeechResult.Error(SpeechErrors.UNKNOWN))
                            close()
                        }
                    }
                }

                override fun onResults(results: Bundle?) {
                    val data = results!!.getStringArrayList(
                        SpeechRecognizer.RESULTS_RECOGNITION
                    )?.get(0)
                    data?.let {
                        trySend(SpeechResult.FinalResult(it))
                    } ?: {
                        trySend(SpeechResult.Error(SpeechErrors.EMPTY_RESULT))
                    }
                    close()
                }

                override fun onPartialResults(partialResults: Bundle?) {
                    val data = partialResults!!.getStringArrayList(
                        SpeechRecognizer.RESULTS_RECOGNITION
                    )?.get(0)
                    data?.let {
                        trySend(SpeechResult.PartialResult(it))
                    } ?: {
                        trySend(SpeechResult.Error(SpeechErrors.EMPTY_RESULT))
                    }
                }

                override fun onEvent(eventType: Int, params: Bundle?) {

                }

            })
            speechRecognizer?.startListening(speechRecognizerIntent)

            awaitClose {
                // If the flow is cancelled, cancel the speech
            }
        }
    }

    override fun stopListening() {
        speechRecognizer?.stopListening()
    }

    override fun destroy() {
        speechRecognizer?.destroy()
    }

    override fun setLanguage(lang: String) {
        speechRecognizerIntent!!.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang)
    }

}