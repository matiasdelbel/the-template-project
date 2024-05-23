package com.tracking.app.model

import java.util.Date

sealed interface Workout {

    data class Running(
        val id: Long,
        val date: Date,
        val distanceKm: Double,
        val duration: TimePeriod,
        val averagePace: TimePeriod,
    )
}

data class TimePeriod(
    val minutes: Int,
    val seconds: Int,
)
