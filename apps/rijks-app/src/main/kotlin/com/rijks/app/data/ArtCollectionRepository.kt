package com.rijks.app.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rijks.app.data.remote.ArtCollectionPagingSource
import com.rijks.app.data.remote.ArtCollectionDataSource
import com.rijks.app.model.ArtObjectOverview
import com.rijks.app.model.ArtObject
import kotlinx.coroutines.flow.Flow

class ArtCollectionRepository internal constructor(
    private val artCollectionDataSource: ArtCollectionDataSource
) {

    fun paginatedArtObjects(pageSize: Int = DEFAULT_PAGE_SIZE): Flow<PagingData<ArtObjectOverview>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = DEFAULT_PREFETCH_DISTANCE
        ),
        pagingSourceFactory = { ArtCollectionPagingSource(artCollectionDataSource) }
    ).flow

    suspend fun obtainArtObject(objectNumber: String): ArtObject = artCollectionDataSource
        .getArtCollectionDetail(objectNumber)
        .artObject
        .toArtObject()
}

private const val DEFAULT_PAGE_SIZE = 20
private const val DEFAULT_PREFETCH_DISTANCE = 60
