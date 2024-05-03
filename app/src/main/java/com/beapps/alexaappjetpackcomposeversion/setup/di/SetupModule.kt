package com.beapps.alexaappjetpackcomposeversion.setup.di

import android.content.Context
import com.beapps.alexaappjetpackcomposeversion.setup.data.SetupRepoImpl
import com.beapps.alexaappjetpackcomposeversion.setup.domain.SetupRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SetupModule {
    @Provides
    @Singleton
    fun provideSetupRepo(@ApplicationContext context: Context): SetupRepo {
        return SetupRepoImpl(
            context
        )
    }
}