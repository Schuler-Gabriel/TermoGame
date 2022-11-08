package com.schuler.termogame.di

import android.content.Context
import androidx.room.Room
import com.schuler.termogame.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideWordsDao(database: AppDatabase): AppDatabaseDao
    = database.appDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase
    = Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        )
        .createFromAsset("database/app.db")
        .build()

}