package com.rijks.app.data.remote

import com.dbel.data.ListedPagingSource
import com.dbel.data.currentPage
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
