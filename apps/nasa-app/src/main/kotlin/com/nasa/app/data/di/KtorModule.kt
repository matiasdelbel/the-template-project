package com.nasa.app.data.di

import android.content.Context
import com.dbel.data.ktor.DefaultHttpClient
import com.nasa.app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal class KtorModule {

    @Provides
    @NasaHttpClient
    fun provideHttpClient(@ApplicationContext context: Context): HttpClient = DefaultHttpClient(
        interceptor = { request ->
            request.parameter("api_key", context.getString(R.string.nasa_api_key))
        }
    )
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NasaHttpClient
