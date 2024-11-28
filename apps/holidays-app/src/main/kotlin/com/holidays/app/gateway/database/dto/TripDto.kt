package com.holidays.app.gateway.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.holidays.app.model.Trip

@Entity(tableName = "trips")
data class TripDto(
    @ColumnInfo(name = "name") val name: String,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toTrip() = Trip(
        id = id,
        name = name,
    )
}

fun Trip.toTripDto() = TripDto(
    name = name,
).apply { id = this@toTripDto.id }
