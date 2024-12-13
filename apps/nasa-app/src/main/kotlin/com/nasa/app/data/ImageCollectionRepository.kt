package com.nasa.app.data

import androidx.paging.PagingData
import com.dbel.data.ListingDataSource
import com.dbel.data.ListingDataSource.ListingResult
import com.dbel.data.currentPage
import com.dbel.data.pagesFlow
import com.nasa.app.data.remote.ImageCollectionDataSource
import com.nasa.app.model.Image
import kotlinx.coroutines.flow.Flow

class ImageCollectionRepository internal constructor(
    private val dataSource: ImageCollectionDataSource
) {

    fun searchImages(query: String): Flow<PagingData<Image>> = pagesFlow {
        ListingDataSource<Image> { params ->
            val imageCollection = dataSource.getImageCollection(
                page = params.currentPage,
                query = query
            )

            ListingResult(
                content = imageCollection.items.map { it.toImage() },
                count = imageCollection.metadata.totalHits.toLong()
            )
        }
    }
}
