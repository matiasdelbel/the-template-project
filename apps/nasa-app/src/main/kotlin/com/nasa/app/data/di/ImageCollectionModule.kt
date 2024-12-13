package com.nasa.app.data.di

import com.nasa.app.data.ImageCollectionRepository
import com.nasa.app.data.remote.ImageCollectionDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class ImageCollectionModule {

    @Provides
    fun provideCollectionRepository(imageCollectionDataSource: ImageCollectionDataSource): ImageCollectionRepository =
        ImageCollectionRepository(imageCollectionDataSource)
}
