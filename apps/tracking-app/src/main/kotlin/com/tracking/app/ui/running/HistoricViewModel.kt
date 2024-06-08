package com.tracking.app.ui.running

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.tracking.app.gateway.WorkoutRepository
import com.tracking.app.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class HistoricViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    val all = workoutRepository
        .workouts
        .map { workouts -> workouts.map { workout -> workout.toUiState() } }

    private fun Workout.Running.toUiState() = WorkoutUiState(
        date = dateFormatter.format(date),
        distanceKm = "$distanceKm km",
        duration = "${duration.minutes}:${duration.seconds}",
        averagePace = "${averagePace.minutes}:${averagePace.seconds}"
    )

    data class WorkoutUiState(
        val date: String,
        val distanceKm: String,
        val duration: String,
        val averagePace: String,
    )
}

@SuppressLint("SimpleDateFormat")
val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
