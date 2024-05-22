package com.beapps.alexaappjetpackcomposeversion.core.domin

import android.content.Context
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import net.gotev.speech.Speech
import net.gotev.speech.TextToSpeechCallback
import java.util.Locale

class SpeakerManagerImpl(
    private val deviceLocalProvider: DeviceLocalProvider
) : SpeakerManager {

    override fun setup() {
        val tag = deviceLocalProvider.getCurrentDeviceLocalTag()
        Speech.getInstance().setLocale(Locale(tag)).say("")
    }

    override fun speak(toBeSpoken: String?): Flow<SpeakerResult> {
        return callbackFlow {
            Speech.getInstance().say(toBeSpoken, object : TextToSpeechCallback {
                override fun onStart() {
                    trySend(SpeakerResult.ON_START)
                }

                override fun onCompleted() {
                    trySend(SpeakerResult.ON_COMPLETED)
                    close()

                }

                override fun onError() {
                    trySend(SpeakerResult.ON_ERROR)
                    close()
                }
            })

            awaitClose {
                // If the flow is cancelled, cancel the speech
                pause()
            }
        }
    }

    override fun pause() {
        if (Speech.getInstance().isSpeaking)
            Speech.getInstance().stopTextToSpeech();
    }
}