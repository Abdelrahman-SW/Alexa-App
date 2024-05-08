package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain

import kotlinx.coroutines.flow.Flow

interface TheSpeechRecognizer {
    fun init()
    fun startListening() : Flow<SpeechResult>
    fun stopListening()

    fun destroy()

    fun setLanguage (lang: String)
}