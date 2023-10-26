package com.tmdb.app.data.remote

import com.tmdb.app.data.dto.MovieCollectionDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieCollectionDataSource {

    @GET("movie/popular")
    suspend fun getPopulars(@Query("page") page: Int,
    ): MovieCollectionDto
}
