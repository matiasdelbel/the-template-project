package com.nasa.app.data.remote.dto

import com.nasa.app.model.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageCollectionDto(
    @SerialName("version") val version: String,
    @SerialName("href") val href: String,
    @SerialName("items") val items: List<ItemDto>,
    @SerialName("metadata") val metadata: MetadataDto,
    @SerialName("links") val links: List<LinkDto>
)

@Serializable
data class ItemDto(
    @SerialName("href") val href: String,
    @SerialName("data") val data: List<DataDto>,
    @SerialName("links") val links: List<LinkDto>
) {
    fun toImage(): Image {
        return Image(
            href = href,
            data = data.map { it.toDataModel() },
            links = links.map { it.toLinkModel() }
        )
    }
}

@Serializable
data class DataDto(
    @SerialName("center") val center: String,
    @SerialName("title") val title: String,
    @SerialName("keywords") val keywords: List<String>,
    @SerialName("nasa_id") val nasaId: String,
    @SerialName("date_created") val dateCreated: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("description") val description: String
) {
    fun toDataModel(): Image.Data {
        return Image.Data(
            center = center,
            title = title,
            keywords = keywords,
            nasaId = nasaId,
            dateCreated = dateCreated,
            mediaType = mediaType,
            description = description
        )
    }
}

@Serializable
data class LinkDto(
    @SerialName("href") val href: String,
    @SerialName("rel") val rel: String,
    @SerialName("render") val render: String? = null,
    @SerialName("prompt") val prompt: String? = null
) {
    fun toLinkModel(): Image.Link {
        return Image.Link(
            href = href,
            rel = rel,
            render = render,
            prompt = prompt
        )
    }
}

@Serializable
data class MetadataDto(
    @SerialName("total_hits") val totalHits: Int
)
