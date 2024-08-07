package com.tmdb.app.data.dto

import com.squareup.moshi.Json
import com.tmdb.app.model.Movie

internal data class MovieCollectionDto(
    val results: List<MovieDto>,
    val page: Int,
    @Json(name = "total_pages") val totalPages: Long,
    @Json(name = "total_results") val totalResults: Long,
)

internal data class MovieDto(
    val id: Long,
    val title: String,
    val overview: String,
    @Json(name = "poster_path") val posterPath: String,
) {
    fun toMovie(): Movie = Movie(
        id = id,
        title = title,
        posterPath = "https://image.tmdb.org/t/p/w500/$posterPath"
    )
}
