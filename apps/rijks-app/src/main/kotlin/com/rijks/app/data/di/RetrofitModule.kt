package com.rijks.app.data.di

import android.content.Context
import com.common.data.retrofit.QueryParamAuthenticator
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
    @RijksOkHttpClient
    fun provideOkHttpClient(@ApplicationContext context: Context) = okHttpClient {
        authenticator(authenticator = QueryParamAuthenticator(
            key = "key",
            token = context.getString(R.string.rijks_api_key)
        ))
    }

    @Provides
    @RijksRetrofit
    fun provideRetrofit(@RijksOkHttpClient client: OkHttpClient) = retrofit {
        baseUrl("https://www.rijksmuseum.nl/api/en/")
        client(client)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RijksOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RijksRetrofit
