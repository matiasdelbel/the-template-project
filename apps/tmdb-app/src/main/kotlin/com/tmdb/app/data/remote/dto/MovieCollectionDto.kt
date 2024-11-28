package com.tmdb.app.data.remote.dto

import com.tmdb.app.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieCollectionDto(
    val results: List<MovieDto>,
    val page: Int,
    @SerialName("total_pages") val totalPages: Long,
    @SerialName("total_results") val totalResults: Long,
)

@Serializable
internal data class MovieDto(
    val id: Long,
    val title: String,
    val overview: String,
    @SerialName("poster_path") val posterPath: String?,
) {
    fun toMovie(): Movie = Movie(
        id = id,
        title = title,
        posterPath = "https://image.tmdb.org/t/p/w500/$posterPath"
    )
}
