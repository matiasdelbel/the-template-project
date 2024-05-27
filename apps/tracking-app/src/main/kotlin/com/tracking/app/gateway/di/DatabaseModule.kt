package com.tracking.app.gateway.di

import android.content.Context
import androidx.room.Room
import com.tracking.app.gateway.database.AppDatabase
import com.tracking.app.gateway.database.WorkoutDao
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
            "workouts"
        )
        .build()

    @Provides
    fun provideWorkoutDao(appDatabase: AppDatabase): WorkoutDao = appDatabase
        .workoutDao()
}