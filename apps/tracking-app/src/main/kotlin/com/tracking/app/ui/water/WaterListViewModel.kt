package com.tracking.app.ui.water

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tracking.app.gateway.WorkoutRepository
import com.tracking.app.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class HistoricViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    val all = workoutRepository
        .workouts
        .map { workouts -> workouts.map { workout -> workout.toUiState() } }

    fun delete(state: WorkoutUiState) = viewModelScope.launch {
        workoutRepository.delete(workout = state.workout)
    }

    private fun Workout.Running.toUiState() = WorkoutUiState(
        averagePace = "${averagePace.minutes}:${averagePace.seconds}",
        date = dateFormatter.format(date),
        distanceKm = "$distanceKm km",
        duration = "${duration.minutes}:${duration.seconds}",
        workout = this,
    )

    data class WorkoutUiState(
        val averagePace: String,
        val date: String,
        val distanceKm: String,
        val duration: String,
        val workout: Workout.Running,
    )
}

@SuppressLint("SimpleDateFormat")
val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
