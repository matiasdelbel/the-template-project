package com.holidays.app.gateway.database.dto

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.holidays.app.model.Place

@Entity(tableName = "places")
data class PlaceDto(
    // @ColumnInfo(name = "google_maps_id") val googleMapsId: String,
    @ColumnInfo(name = "name") val name: String,
    @Embedded(prefix = "geo") val coordinates: CoordinateDto,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toPlace() = Place(
        id = id,
        // googleMapsId = googleMapsId,
        name = name,
        coordinate = coordinates.toCoordinate()
    )
}

fun Place.toPlaceDto() = PlaceDto(
    // googleMapsId = googleMapsId,
    name = name,
    coordinates = coordinate.toCoordinateDto()
).apply { id = this@toPlaceDto.id }
