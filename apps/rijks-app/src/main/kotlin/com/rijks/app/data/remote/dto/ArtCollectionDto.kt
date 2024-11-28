package com.rijks.app.data.remote.dto

import com.rijks.app.model.ArtObjectOverview
import kotlinx.serialization.Serializable

@Serializable
internal data class ArtCollectionDto(
    val count: Long,
    val artObjects: List<ArtObjectOverviewDto>
)

@Serializable
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

@Serializable
internal data class WebImageDto(
    val url: String,
)
