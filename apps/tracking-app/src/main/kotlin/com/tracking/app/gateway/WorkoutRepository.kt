package com.tracking.app.gateway

import com.tracking.app.gateway.database.WorkoutDao
import com.tracking.app.gateway.database.dto.toRunningDto
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

    suspend fun findById(workoutId: Long): Workout.Running = workoutDao
        .findById(workoutId)
        .toWorkoutRunning()

    suspend fun delete(workout: Workout.Running) = workoutDao
        .delete(workout = workout.toRunningDto())

    suspend fun insert(workout: Workout.Running) = workoutDao
        .insert(workout = workout.toRunningDto())

    suspend fun update(workout: Workout.Running) = workoutDao
        .update(workout = workout.toRunningDto())
}
