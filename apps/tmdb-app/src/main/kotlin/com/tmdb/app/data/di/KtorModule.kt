package com.tmdb.app.data.di

import android.content.Context
import com.dbel.data.ktor.DefaultHttpClient
import com.tmdb.app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal class KtorModule {

    @Provides
    @TMdbHttpClient
    fun provideHttpClient(@ApplicationContext context: Context) = DefaultHttpClient {
        install(plugin = Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                       accessToken = context.getString(R.string.tmdb_api_key),
                        refreshToken = context.getString(R.string.tmdb_api_key)
                    )
                }
            }
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TMdbHttpClient
