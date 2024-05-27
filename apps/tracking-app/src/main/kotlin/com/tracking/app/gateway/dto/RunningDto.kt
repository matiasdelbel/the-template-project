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
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "distance_km") val distanceKm: Double,
    @Embedded(prefix = "duration") val duration: TimePeriodDto,
    @Embedded(prefix = "average_pace") val averagePace: TimePeriodDto,
) {

    @PrimaryKey(autoGenerate = true) var id: Long = 0

    fun toWorkoutRunning() = Workout.Running(
        id = id,
        date = date,
        distanceKm = distanceKm,
        duration = duration.toTimePeriod(),
        averagePace = averagePace.toTimePeriod(),
    )
}

fun Workout.Running.toRunningDto() = RunningDto(
    date = date,
    distanceKm = distanceKm,
    duration = duration.toTimePeriodDto(),
    averagePace = averagePace.toTimePeriodDto()
).apply { id = this@toRunningDto.id }
