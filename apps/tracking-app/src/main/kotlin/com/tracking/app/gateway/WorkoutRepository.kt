package com.tracking.app.gateway

import com.tracking.app.gateway.database.WorkoutDao
import com.tracking.app.gateway.dto.toRunningDto
import com.tracking.app.model.Workout
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor(
    private val workoutDao: WorkoutDao
) {

    val workouts = workoutDao
        .loadAll()
        .map { it.map { dto -> dto.toWorkoutRunning() } }

    suspend fun insert(workout: Workout.Running) {
        workoutDao.insert(workout = workout.toRunningDto())
    }
}
