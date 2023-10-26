package com.tmdb.app.data.di

import android.content.Context
import com.common.data.retrofit.BearerTokenAuthenticator
import com.common.data.retrofit.okHttpClient
import com.common.data.retrofit.retrofit
import com.tmdb.app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {

    @Provides
    @TmdbOkHttpClient
    fun provideOkHttpClient(@ApplicationContext context: Context) = okHttpClient {
        authenticator(BearerTokenAuthenticator(token = context.getString(R.string.tmdb_api_key)))
    }

    @Provides
    @TmdbRetrofit
    fun provideRetrofit(@TmdbOkHttpClient client: OkHttpClient) = retrofit {
        baseUrl("https://api.themoviedb.org/3/")
        client(client)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TmdbOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TmdbRetrofit
