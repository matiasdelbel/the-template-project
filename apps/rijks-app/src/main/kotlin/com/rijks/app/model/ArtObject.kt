package com.rijks.app.model

data class ArtObject(
    val subtitle: String = "",
    val description: String = "",
    val date: String = "",
    val materials: List<String> = emptyList(),
    val principalMakers: List<String> = emptyList(),
)
