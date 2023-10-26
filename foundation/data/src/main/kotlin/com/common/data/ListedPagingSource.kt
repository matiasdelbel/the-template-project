package com.common.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

abstract class ListedPagingSource<T : Any> : PagingSource<Int, T>() {

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
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = state.anchorPosition

    abstract suspend fun loadMore(params: LoadParams<Int>): LoadListingResult<T>

    data class LoadListingResult<T>(
        val content: List<T>,
        val count: Long
    )
}

val PagingSource.LoadParams<Int>.currentPage : Int get() = key ?: 1
