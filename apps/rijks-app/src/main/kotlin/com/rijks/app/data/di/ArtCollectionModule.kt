package com.rijks.app.data.di

import com.rijks.app.data.ArtCollectionRepository
import com.rijks.app.data.remote.ArtCollectionDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class ArtCollectionModule {

    @Provides
    fun provideCollectionRepository(artCollectionDataSource: ArtCollectionDataSource): ArtCollectionRepository =
        ArtCollectionRepository(artCollectionDataSource)
}
