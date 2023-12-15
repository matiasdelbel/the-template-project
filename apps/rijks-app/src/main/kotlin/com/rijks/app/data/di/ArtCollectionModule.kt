package com.rijks.app.data.di

import com.rijks.app.data.ArtCollectionRepository
import com.rijks.app.data.remote.ArtCollectionDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ArtCollectionModule {

    @Provides
    fun provideRemoteDataSource(
        @RijksRetrofit retrofit: Retrofit
    ): ArtCollectionDataSource = retrofit.create(ArtCollectionDataSource::class.java)

    @Provides
    fun provideCollectionRepository(artCollectionDataSource: ArtCollectionDataSource): ArtCollectionRepository =
        ArtCollectionRepository(artCollectionDataSource)
}
