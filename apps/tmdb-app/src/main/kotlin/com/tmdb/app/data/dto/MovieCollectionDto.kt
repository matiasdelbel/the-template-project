package com.tmdb.app.data.dto

import com.tmdb.app.model.Movie

internal data class MovieCollectionDto(
    val results: List<MovieDto>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
)

internal data class MovieDto(
    val id: Long,
    val title: String,
    val overview: String,
    val poster_path: String,
) {
    fun toMovie(): Movie = Movie(
        id = id,
        title = title,
        posterPath = "https://image.tmdb.org/t/p/w500/$poster_path"
    )
}
