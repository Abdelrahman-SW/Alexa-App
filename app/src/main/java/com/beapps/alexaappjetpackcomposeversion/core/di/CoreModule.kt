package com.beapps.alexaappjetpackcomposeversion.core.di

import android.content.Context
import com.beapps.alexaappjetpackcomposeversion.core.domin.SpeakerManagerImpl
import com.beapps.alexaappjetpackcomposeversion.core.domin.SpeakerManager
import com.beapps.alexaappjetpackcomposeversion.core.domin.TranslatorDownloader
import com.beapps.alexaappjetpackcomposeversion.core.domin.TranslatorDownloaderMlKitImpl
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
object CoreModule {
    @Provides
    @Singleton
    fun provideSpeakerManager() : SpeakerManager {
        return SpeakerManagerImpl()
    }


    @Provides
    @Singleton
    fun provideTranslatorManager() : TranslatorDownloader {
        return TranslatorDownloaderMlKitImpl()
    }

}