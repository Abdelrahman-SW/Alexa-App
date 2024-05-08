package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain

enum class SupportedLanguages(val tag : String) {
        ENGLISH("en"),
        ARABIC("ar"),
        FRENCH("fr"),
        GERMAN("de"),
        INDONESIAN("id"),
        JAPANESE("ja"),
        PORTUGUESE("pt"),
        RUSSIAN("ru"),
        CHINESE("zh-CN"),
        SPANISH("es")
}


val languages = SupportedLanguages.entries.toTypedArray()


