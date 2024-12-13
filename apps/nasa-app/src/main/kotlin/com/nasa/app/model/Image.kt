package com.nasa.app.model

data class Image(
    val href: String,
    val data: List<Data>,
    val links: List<Link>
) {
    data class Data(
        val center: String,
        val title: String,
        val keywords: List<String>,
        val nasaId: String,
        val dateCreated: String,
        val mediaType: String,
        val description: String
    )

    data class Link(
        val href: String,
        val rel: String,
        val render: String? = null,
        val prompt: String? = null
    )
}