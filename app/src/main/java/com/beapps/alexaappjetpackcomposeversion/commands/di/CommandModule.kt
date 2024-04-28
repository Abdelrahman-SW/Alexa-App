package com.beapps.alexaappjetpackcomposeversion.commands.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.beapps.alexaappjetpackcomposeversion.commands.data.db.CommandDatabase
import com.beapps.alexaappjetpackcomposeversion.commands.data.repo.CommandsRepoImpl
import com.beapps.alexaappjetpackcomposeversion.commands.domain.CommandsRepo
import com.beapps.alexaappjetpackcomposeversion.commands.domain.SpeakerManager
import com.beapps.alexaappjetpackcomposeversion.commands.domain.SpeakerManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CommandModule {
    @Provides
    @Singleton
    fun provideCommandDatabase(app: Application): CommandDatabase {
        return Room.databaseBuilder(
            app,
            CommandDatabase::class.java,
            CommandDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCommandRepo(@ApplicationContext context: Context , commandDatabase: CommandDatabase): CommandsRepo {
        return CommandsRepoImpl(
            context,
            commandDatabase
        )
    }

    @Provides
    @Singleton
    fun provideSpeakerManager() : SpeakerManager {
        return SpeakerManagerImpl()
    }
}