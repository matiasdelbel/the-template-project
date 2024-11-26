package com.tmdb.app.data

import androidx.paging.PagingData
import com.dbel.data.ListingDataSource
import com.dbel.data.ListingDataSource.ListingResult
import com.dbel.data.currentPage
import com.dbel.data.pagesFlow
import com.tmdb.app.data.remote.MoviesDataSource
import com.tmdb.app.model.Movie
import kotlinx.coroutines.flow.Flow

class MoviesRepository internal constructor(
    private val dataSource: MoviesDataSource,
) {

    fun paginatedNowPlayingMovies(): Flow<PagingData<Movie>> = pagesFlow {
        ListingDataSource<Movie> { params ->
            val movies = dataSource.nowPlaying(page = params.currentPage)

            ListingResult(
                content = movies.results.map { it.toMovie() },
                count = movies.totalPages
            )
        }
    }

    fun popularMovies(): Flow<PagingData<Movie>> = pagesFlow {
        ListingDataSource<Movie> { params ->
            val movies = dataSource.populars(page = params.currentPage)

            ListingResult(
                content = movies.results.map { it.toMovie() },
                count = movies.totalPages
            )
        }
    }

    fun topRatedMovies(): Flow<PagingData<Movie>> = pagesFlow {
        ListingDataSource<Movie> { params ->
            val movies = dataSource.topRated(page = params.currentPage)

            ListingResult(
                content = movies.results.map { it.toMovie() },
                count = movies.totalPages
            )
        }
    }

    fun upcomingMovies(): Flow<PagingData<Movie>> = pagesFlow {
        ListingDataSource<Movie> { params ->
            val movies = dataSource.upcoming(page = params.currentPage)

            ListingResult(
                content = movies.results.map { it.toMovie() },
                count = movies.totalPages
            )
        }
    }

    fun search(query: String): Flow<PagingData<Movie>> = pagesFlow {
        ListingDataSource<Movie> { params ->
            val movies = dataSource.search(query, page = params.currentPage)

            ListingResult(
                content = movies.results.map { it.toMovie() },
                count = movies.totalPages
            )
        }
    }
}
