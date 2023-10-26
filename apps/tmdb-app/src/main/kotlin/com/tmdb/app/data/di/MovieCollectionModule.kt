package com.tmdb.app.data.di

import com.tmdb.app.data.MoviesRepository
import com.tmdb.app.data.remote.MovieCollectionDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class MovieCollectionModule {

    @Provides
    fun provideRemoteDataSource(
        @TmdbRetrofit retrofit: Retrofit
    ): MovieCollectionDataSource = retrofit.create(MovieCollectionDataSource::class.java)

    @Provides
    fun provideCollectionRepository(dataSource: MovieCollectionDataSource): MoviesRepository =
        MoviesRepository(dataSource)
}
