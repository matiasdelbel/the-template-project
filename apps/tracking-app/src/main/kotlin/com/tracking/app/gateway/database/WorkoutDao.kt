package com.tracking.app.gateway.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tracking.app.gateway.dto.RunningDto
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Delete
    suspend fun delete(workout: RunningDto)

    @Query("SELECT * FROM running WHERE id = :workoutId")
    suspend fun findById(workoutId: Long): RunningDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: RunningDto)

    @Query("SELECT * FROM running")
    fun loadAll(): Flow<List<RunningDto>>

    @Update
    suspend fun update(workout: RunningDto)
}
