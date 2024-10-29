package com.holidays.app.model

data class Place(
    internal val id: Long,
    // internal val googleMapsId: String,
    val name: String,
    val coordinate: Coordinate,
)

data class Coordinate(
    val latitude: Double,
    val longitude: Double,
)
