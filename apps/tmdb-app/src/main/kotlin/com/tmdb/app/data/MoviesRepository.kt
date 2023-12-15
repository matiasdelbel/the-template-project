package com.tmdb.app.data

import androidx.paging.PagingData
import com.common.data.pagesFlow
import com.tmdb.app.data.remote.MovieCollectionDataSource
import com.tmdb.app.data.remote.PopularMoviesPagingSource
import com.tmdb.app.data.remote.TopRatedMoviesPagingSource
import com.tmdb.app.data.remote.UpcomingMoviesPagingSource
import com.tmdb.app.model.Movie
import kotlinx.coroutines.flow.Flow

class MoviesRepository internal constructor(
    private val dataSource: MovieCollectionDataSource
) {

    fun paginatedPopularMovies(): Flow<PagingData<Movie>> =
        pagesFlow { PopularMoviesPagingSource(dataSource) }

    fun paginatedTopRatedMovies(): Flow<PagingData<Movie>> =
        pagesFlow { TopRatedMoviesPagingSource(dataSource) }

    fun paginatedUpcomingMovies(): Flow<PagingData<Movie>> =
        pagesFlow { UpcomingMoviesPagingSource(dataSource) }
}
