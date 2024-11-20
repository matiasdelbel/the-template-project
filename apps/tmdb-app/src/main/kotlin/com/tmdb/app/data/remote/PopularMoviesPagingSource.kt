package com.tmdb.app.data.remote

import com.dbel.data.ListedPagingSource
import com.dbel.data.currentPage
import com.tmdb.app.model.Movie

internal class PopularMoviesPagingSource(
    private val movieDataSource: MoviesDataSource,
) : ListedPagingSource<Movie>() {

    override suspend fun loadMore(params: LoadParams<Int>): LoadListingResult<Movie> {
        val movies = movieDataSource.populars(page = params.currentPage)

        return LoadListingResult(
            content = movies.results.map { it.toMovie() },
            count = movies.totalPages
        )
    }
}
