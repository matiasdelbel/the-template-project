package com.rijks.app.data.dto

import com.rijks.app.model.ArtObject
import kotlinx.serialization.Serializable

@Serializable
internal data class ArtCollectionDetailDto(
    val artObject: ArtObjectDto,
)

@Serializable
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

@Serializable
internal data class MakerDto(
    val name: String,
)

@Serializable
internal data class DatingDto(
    val presentingDate: String
)
