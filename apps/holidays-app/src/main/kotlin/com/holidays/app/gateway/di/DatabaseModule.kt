package com.holidays.app.gateway.di

import android.content.Context
import androidx.room.Room
import com.holidays.app.gateway.database.AppDatabase
import com.holidays.app.gateway.database.PlacesDao
import com.holidays.app.gateway.database.TripDao
import com.holidays.app.gateway.database.TripLinksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "holidays"
        )
        .build()

    @Provides
    fun providePlacesDao(appDatabase: AppDatabase): PlacesDao = appDatabase
        .placesDao()

    @Provides
    fun provideTripsDao(appDatabase: AppDatabase): TripDao = appDatabase
        .tripsDao()

    @Provides
    fun provideTripsLinksDao(appDatabase: AppDatabase): TripLinksDao = appDatabase
        .tripLinksDao()
}