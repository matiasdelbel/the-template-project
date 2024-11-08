package com.tmdb.app.data.di

import com.tmdb.app.data.MoviesRepository
import com.tmdb.app.data.remote.MoviesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class MovieCollectionModule {

    @Provides
    fun provideCollectionRepository(
        moviesDataSource: MoviesDataSource,
    ): MoviesRepository =
        MoviesRepository(dataSource = moviesDataSource)
}
