package com.rijks.app.data.di

import android.content.Context
import com.rijks.app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal class KtorModule {

    @Provides
    @RijksHttpClient
    fun provideHttpClient(@ApplicationContext context: Context) = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }.apply {
        plugin(HttpSend).intercept { request ->
            request.parameter("key", context.getString(R.string.rijks_api_key))
            execute(request)
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RijksHttpClient
