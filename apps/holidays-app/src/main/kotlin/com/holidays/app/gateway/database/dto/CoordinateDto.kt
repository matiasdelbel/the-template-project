package com.holidays.app.gateway.database.dto

import androidx.room.ColumnInfo
import com.holidays.app.model.Coordinate

data class CoordinateDto(
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
) {

    fun toCoordinate() = Coordinate(latitude, longitude)
}

fun Coordinate.toCoordinateDto() = CoordinateDto(latitude, longitude)
