package com.beapps.alexaappjetpackcomposeversion.core.di

import android.content.Context
import com.beapps.alexaappjetpackcomposeversion.core.domin.DeviceLocalProvider
import com.beapps.alexaappjetpackcomposeversion.core.domin.DeviceLocalProviderAndroidImpl
import com.beapps.alexaappjetpackcomposeversion.core.domin.KeyStoreManagementSharedPrefsImpl
import com.beapps.alexaappjetpackcomposeversion.core.domin.KeysStoreManagement
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
    fun provideSpeakerManager(deviceLocalProvider: DeviceLocalProvider) : SpeakerManager {
        return SpeakerManagerImpl(deviceLocalProvider)
    }

    @Provides
    @Singleton
    fun provideKeyStoreManagement(@ApplicationContext context: Context) : KeysStoreManagement {
        return KeyStoreManagementSharedPrefsImpl(context)
    }

    @Provides
    @Singleton
    fun provideDeviceLocalProvider(@ApplicationContext context: Context) : DeviceLocalProvider {
        return DeviceLocalProviderAndroidImpl(context)
    }


    @Provides
    @Singleton
    fun provideTranslatorManager() : TranslatorDownloader {
        return TranslatorDownloaderMlKitImpl()
    }



}