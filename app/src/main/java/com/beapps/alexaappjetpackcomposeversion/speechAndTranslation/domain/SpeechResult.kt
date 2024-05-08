package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain


sealed class SpeechResult {
        data object ReadyToListen : SpeechResult()
        data object EndOfSpeech : SpeechResult()
        data class  PartialResult(val result: String) : SpeechResult()
        data class  FinalResult(val result: String) : SpeechResult()
        data class  Error(val error: SpeechErrors) : SpeechResult()
}

enum class SpeechErrors {
        BUSY,
        NETWORK_ERROR,
        UNKNOWN,
        EMPTY_RESULT
}

