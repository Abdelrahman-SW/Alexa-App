package com.beapps.alexaappjetpackcomposeversion.core.domin

import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language


interface SpeakerManager {
    fun setup(language: String = "en")

    fun speak(toBeSpoken: String?) : Flow<SpeakerResult>

    fun pause()
}


enum class SpeakerResult {
    ON_START,
    ON_COMPLETED,
    ON_ERROR
}