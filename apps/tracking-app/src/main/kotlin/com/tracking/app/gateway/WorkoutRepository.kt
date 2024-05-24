package com.tracking.app.gateway

import android.annotation.SuppressLint
import com.tracking.app.model.Workout
import com.tracking.app.model.TimePeriod
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor() {

    private val historicRunning = listOf(
        Workout.Running(
            id = 1L,
            distanceKm = 3.01,
            duration = TimePeriod(minutes = 20, seconds = 18),
            date = Date(date = "22-05-2024"),
            averagePace = TimePeriod(minutes = 6, seconds = 44)
        ),
    )

    fun runningHistoric(): Flow<List<Workout.Running>> = flow {
        delay(timeMillis = 400L)

        emit(historicRunning)
    }
}

@SuppressLint("SimpleDateFormat")
private fun Date(date: String): Date {
    val dateFormatter = SimpleDateFormat("dd-MM-yyyy")

    return dateFormatter.parse(date) as Date
}
