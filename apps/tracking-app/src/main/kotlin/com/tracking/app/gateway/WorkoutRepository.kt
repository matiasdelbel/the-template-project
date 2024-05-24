package com.tracking.app.gateway

import com.tracking.app.gateway.database.AppDatabase
import com.tracking.app.gateway.database.WorkoutDao
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor(workoutDao: WorkoutDao) {

    val workouts = workoutDao
        .loadAll()
        .map { it.map { dto -> dto.toWorkoutRunning() } }
}
