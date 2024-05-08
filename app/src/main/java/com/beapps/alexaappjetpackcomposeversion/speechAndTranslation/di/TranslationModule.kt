package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.di

import android.content.Context
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TheSpeechRecognizer
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TheSpeechRecognizerImpl
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.Translator
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TranslatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TranslationModule {

    @Provides
    @Singleton
    fun provideSpeechRecognizer(@ApplicationContext context: Context): TheSpeechRecognizer {
        return TheSpeechRecognizerImpl(
            context
        )
    }


    @Provides
    @Singleton
    fun provideTranslator(): Translator {
        return TranslatorImpl()

    }
}