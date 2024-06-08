package com.tracking.app.gateway.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tracking.app.gateway.dto.RunningDto
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM running")
    fun loadAll(): Flow<List<RunningDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: RunningDto)

    @Delete
    suspend fun delete(workout: RunningDto)
}
