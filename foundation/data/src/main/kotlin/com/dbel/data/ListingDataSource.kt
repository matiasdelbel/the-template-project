package com.dbel.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import java.io.IOException

class ListingDataSource<T : Any>(
    private val loadMore: suspend (LoadParams<Int>) -> ListingResult<T>,
) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val currentPage = params.currentPage
            val result = loadMore(params)
            val nextArtObjectIndex = (currentPage * params.loadSize) + 1

            LoadResult.Page(
                data = result.content,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (nextArtObjectIndex > result.count) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = state.anchorPosition

    data class ListingResult<T>(
        val content: List<T>,
        val count: Long
    )
}


fun <T: Any> pagesFlow(source: () -> PagingSource<Int, T>) = Pager(
    config = PagingConfig(
        pageSize = 20,
        prefetchDistance = 60
    ),
    pagingSourceFactory = source
).flow

val PagingSource.LoadParams<Int>.currentPage : Int get() = key ?: 1