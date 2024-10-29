package com.holidays.app.model

data class Trip(
    internal val id: Long,
    val name: String,
    var links: List<TripLink> = emptyList(),
) {

    operator fun plusAssign(link: TripLink) {
        links += link
    }
}

data class TripLink(
    internal val id: Long,
    val url: String,
)
