package com.tmdb.app.data.di

import android.content.Context
import com.tmdb.app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal class KtorModule {

    @Provides
    @TMdbHttpClient
    fun provideHttpClient(@ApplicationContext context: Context) = HttpClient {
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                       accessToken = context.getString(R.string.tmdb_api_key),
                        refreshToken = context.getString(R.string.tmdb_api_key)
                    )
                }
            }
        }

        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TMdbHttpClient
