package com.rijks.app.data.dto

import com.rijks.app.model.ArtObjectOverview

internal data class ArtCollectionDto(
    val count: Long,
    val artObjects: List<ArtObjectOverviewDto>
)

internal data class ArtObjectOverviewDto(
    val objectNumber: String,
    val title: String,
    val longTitle: String,
    val principalOrFirstMaker: String,
    val webImage: WebImageDto
) {

    fun toArtObject() = ArtObjectOverview(
        objectNumber = objectNumber,
        title = title,
        longTitle = longTitle,
        principalOrFirstMaker = principalOrFirstMaker,
        imageUrl = webImage.url
    )
}

internal data class WebImageDto(
    val url: String,
)
