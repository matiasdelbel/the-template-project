package com.rijks.app.data

import androidx.paging.PagingData
import com.dbel.data.ListingDataSource
import com.dbel.data.ListingDataSource.ListingResult
import com.dbel.data.currentPage
import com.dbel.data.pagesFlow
import com.rijks.app.data.remote.ArtCollectionDataSource
import com.rijks.app.model.ArtObjectOverview
import com.rijks.app.model.ArtObject
import kotlinx.coroutines.flow.Flow

class ArtCollectionRepository internal constructor(
    private val dataSource: ArtCollectionDataSource
) {

    fun collections(): Flow<PagingData<ArtObjectOverview>> = pagesFlow {
        ListingDataSource<ArtObjectOverview> { params ->
            val artCollection = dataSource.getArtCollection(
                page = params.currentPage,
                pageCount = params.loadSize
            )

            ListingResult(
                content = artCollection.artObjects.map { it.toArtObject() },
                count = artCollection.count
            )
        }
    }

    fun search(query: String): Flow<PagingData<ArtObjectOverview>> = pagesFlow {
        ListingDataSource<ArtObjectOverview> { params ->
            val artObjects = dataSource.search(query, page = params.currentPage, pageCount = params.loadSize)

            ListingResult(
                content = artObjects.artObjects.map { it.toArtObject() },
                count = artObjects.count
            )
        }
    }

    suspend fun obtainArtObject(objectNumber: String): ArtObject = dataSource
        .getArtCollectionDetail(objectNumber)
        .artObject
        .toArtObject()
}
