package com.beapps.alexaappjetpackcomposeversion.commands.domain

import kotlinx.coroutines.flow.Flow


interface SpeakerManager {
    fun setup()

    fun speak(toBeSpoken: String?) : Flow<SpeechResult>

    fun pause()
}


enum class SpeechResult {
    ON_START,
    ON_COMPLETED,
    ON_ERROR
}