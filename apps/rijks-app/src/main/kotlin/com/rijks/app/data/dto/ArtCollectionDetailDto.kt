package com.rijks.app.data.dto

import com.rijks.app.model.ArtObject

internal data class ArtCollectionDetailDto(
    val artObject: ArtObjectDto,
)

internal data class ArtObjectDto(
    val subTitle: String,
    val description: String?,
    val plaqueDescriptionEnglish: String?,
    val materials: List<String>,
    val principalMakers: List<MakerDto>,
    val dating: DatingDto
) {

    fun toArtObject() = ArtObject(
        subtitle = subTitle,
        description = plaqueDescriptionEnglish ?: description.orEmpty(),
        materials = materials,
        principalMakers = principalMakers.map { it.name },
        date = dating.presentingDate
    )
}

internal data class MakerDto(
    val name: String,
)

internal data class DatingDto(
    val presentingDate: String
)