package com.example.newszapp.di

import android.content.Context
import com.example.newszapp.data.database.NewsDao
import com.example.newszapp.data.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase.getDatabase(context)
    }

    @Provides
    fun provideNewsDao(appDatabase: NewsDatabase): NewsDao {
        return appDatabase.newsDao()
    }
}