package com.tracking.app.gateway.dto

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tracking.app.model.Workout
import java.util.Date

@Entity(
    tableName = "running"
)
data class RunningDto(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "distance_km") val distanceKm: Double,
    @Embedded(prefix = "duration") val duration: TimePeriodDto,
    @Embedded(prefix = "average_pace") val averagePace: TimePeriodDto,
) {

    fun toWorkoutRunning() = Workout.Running(
        id = id,
        date = date,
        distanceKm = distanceKm,
        duration = duration.toTimePeriod(),
        averagePace = averagePace.toTimePeriod(),
    )
}
