package com.tmdb.app.data.remote

import com.common.data.ListedPagingSource
import com.common.data.currentPage
import com.tmdb.app.model.Movie

internal class UpcomingMoviesPagingSource(
    private val movieDataSource: MoviesDataSource,
) : ListedPagingSource<Movie>() {

    override suspend fun loadMore(params: LoadParams<Int>): LoadListingResult<Movie> {
        val movies = movieDataSource.upcoming(page = params.currentPage)

        return LoadListingResult(
            content = movies.results.map { it.toMovie() },
            count = movies.totalPages
        )
    }
}
