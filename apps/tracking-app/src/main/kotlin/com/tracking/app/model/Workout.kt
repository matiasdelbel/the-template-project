package com.tracking.app.model

import java.util.Date

sealed interface Workout {

    data class Running(
        val id: Long = 0,
        val date: Date,
        val distanceKm: Double,
        val duration: TimePeriod,
        val averagePace: TimePeriod,
    ) {
        companion object {

            val EmptyRecord = Running(
                date = Date(),
                distanceKm = 0.0,
                duration = TimePeriod(0, 0),
                averagePace = TimePeriod(0, 0),
            )
        }
    }
}

data class TimePeriod(
    val minutes: Int,
    val seconds: Int,
) {

    override fun toString(): String = "$minutes:$seconds"
}
