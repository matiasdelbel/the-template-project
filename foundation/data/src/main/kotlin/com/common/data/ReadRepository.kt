package com.common.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ReadRepository<T: Any> {

    fun pages(
        pageSize: Int = DEFAULT_PAGE_SIZE,
        prefetchDistance: Int = DEFAULT_PREFETCH_DISTANCE
    ): Flow<PagingData<T>>
}

private const val DEFAULT_PAGE_SIZE = 20
private const val DEFAULT_PREFETCH_DISTANCE = 60
