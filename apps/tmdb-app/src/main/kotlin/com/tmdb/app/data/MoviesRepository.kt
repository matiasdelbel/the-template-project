package com.tmdb.app.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tmdb.app.data.remote.MovieCollectionDataSource
import com.tmdb.app.data.remote.PopularMoviesPagingSource
import com.tmdb.app.model.Movie
import kotlinx.coroutines.flow.Flow

class MoviesRepository internal constructor(
    private val dataSource: MovieCollectionDataSource
) {

    fun paginatedPopularMovies(pageSize: Int = DEFAULT_PAGE_SIZE): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = DEFAULT_PREFETCH_DISTANCE
        ),
        pagingSourceFactory = { PopularMoviesPagingSource(dataSource) }
    ).flow
}

private const val DEFAULT_PAGE_SIZE = 20
private const val DEFAULT_PREFETCH_DISTANCE = 60
