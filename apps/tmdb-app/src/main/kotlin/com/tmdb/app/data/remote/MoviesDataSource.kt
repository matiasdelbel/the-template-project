package com.tmdb.app.data.remote

import com.tmdb.app.data.di.TMdbHttpClient
import com.tmdb.app.data.dto.MovieCollectionDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MoviesDataSource @Inject constructor(
    @TMdbHttpClient private val httpClient: HttpClient,
) {

    suspend fun nowPlaying(page: Int): MovieCollectionDto = page(path = "movie/now_playing", page)

    suspend fun populars(page: Int): MovieCollectionDto = page(path = "movie/popular", page)

    suspend fun topRated(page: Int): MovieCollectionDto = page(path = "movie/top_rated", page)

    suspend fun upcoming(page: Int): MovieCollectionDto = page(path = "movie/upcoming", page)

    private suspend fun page(path: String, page: Int): MovieCollectionDto = httpClient
        .get(urlString = "https://api.themoviedb.org/3/$path") {
            url { parameters.append("page", page.toString()) }
        }
        .body()
}
