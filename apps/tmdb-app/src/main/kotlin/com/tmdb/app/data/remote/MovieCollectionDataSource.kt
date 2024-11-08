package com.tmdb.app.data.remote

import com.tmdb.app.data.dto.MovieCollectionDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieCollectionDataSource {

    @GET("movie/now_playing")
    suspend fun nowPlaying(@Query("page") page: Int): MovieCollectionDto

    @GET("movie/popular")
    suspend fun populars(@Query("page") page: Int): MovieCollectionDto

    @GET("movie/top_rated")
    suspend fun topRated(@Query("page") page: Int): MovieCollectionDto

    @GET("movie/upcoming")
    suspend fun upcoming(@Query("page") page: Int): MovieCollectionDto
}
