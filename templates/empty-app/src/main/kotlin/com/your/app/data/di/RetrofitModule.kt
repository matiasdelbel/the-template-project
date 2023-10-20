package com.your.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator.Companion.NONE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {

    @Provides
    @Authenticator
    fun provideAuthenticator(): okhttp3.Authenticator = NONE // TODO replace with your Authenticator

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "https://www.google.com" // TODO replace with your API Base Url

    @Provides
    fun provideOkHttpClient(
        @Authenticator authenticator: okhttp3.Authenticator
    ): OkHttpClient = OkHttpClient
        .Builder()
        .authenticator(authenticator)
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(Level.BASIC) })
        .build()

    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        client: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class Authenticator

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class BaseUrl
