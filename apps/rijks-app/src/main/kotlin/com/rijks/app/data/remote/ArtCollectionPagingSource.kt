package com.rijks.app.data.remote

import com.common.data.ListedPagingSource
import com.common.data.currentPage
import com.rijks.app.model.ArtObjectOverview

internal class ArtCollectionPagingSource(
    private val artCollectionDataSource: ArtCollectionDataSource,
) : ListedPagingSource<ArtObjectOverview>() {

    override suspend fun loadMore(params: LoadParams<Int>): LoadListingResult<ArtObjectOverview> {
        val artCollection = artCollectionDataSource.getArtCollection(
            page = params.currentPage,
            pageCount = params.loadSize
        )

        return LoadListingResult(
            content = artCollection.artObjects.map { it.toArtObject() },
            count = artCollection.count
        )
    }
}
