package com.beapps.alexaappjetpackcomposeversion.commands.domain

import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import net.gotev.speech.Speech
import net.gotev.speech.TextToSpeechCallback
import java.util.Locale

class SpeakerManagerImpl : SpeakerManager {

    companion object {
        const val languageTag = "en"
    }

    override fun setup() {
        Speech.getInstance().setLocale(Locale(languageTag)).say("")
    }

    override fun speak(toBeSpoken: String?): Flow<SpeechResult> {
        return callbackFlow  {
            Speech.getInstance().say(toBeSpoken, object : TextToSpeechCallback {
                override fun onStart() {
                   trySend(SpeechResult.ON_START)
                }

                override fun onCompleted() {
                    trySend(SpeechResult.ON_COMPLETED)
                    close()

                }

                override fun onError() {
                    trySend(SpeechResult.ON_ERROR)
                    close()
                }
            })

            awaitClose {
                // If the flow is cancelled, cancel the speech
                Log.d("ab_do", "flow is cancelled")
                pause()
            }
        }
    }

    override fun pause() {
        if (Speech.getInstance().isSpeaking)
            Speech.getInstance().stopTextToSpeech();
    }
}