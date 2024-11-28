package com.holidays.app.gateway.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.holidays.app.model.TripLink

@Entity(
    tableName = "trips_links",
    foreignKeys = [
        ForeignKey(
            entity = TripDto::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("trip_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TripLinksDto(
    @ColumnInfo(name = "trip_id") val tripId: Long,
    @ColumnInfo(name = "url") val url: String,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toTripLink() = TripLink(
        id = id,
        url = url,
    )
}

fun TripLink.toTripDto(tripId: Long) = TripLinksDto(
    tripId = tripId,
    url = url,
).apply { id = this@toTripDto.id }
