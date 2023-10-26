package com.tmdb.app.data.remote

import com.common.data.ListedPagingSource
import com.common.data.currentPage
import com.tmdb.app.model.Movie

internal class PopularMoviesPagingSource(
    private val movieCollectionDataSource: MovieCollectionDataSource,
) : ListedPagingSource<Movie>() {

    override suspend fun loadMore(params: LoadParams<Int>): LoadListingResult<Movie> {
        val popularMovies = movieCollectionDataSource.getPopulars(page = params.currentPage)

        return LoadListingResult(
            content = popularMovies.results.map { it.toMovie() },
            count = popularMovies.totalPages.toLong()
        )
    }
}
