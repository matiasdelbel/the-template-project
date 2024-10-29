package com.holidays.app.gateway.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.holidays.app.gateway.dto.TripDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {

    @Delete
    suspend fun delete(trip: TripDto)

    @Query("SELECT * FROM trips WHERE id = :tripId")
    suspend fun findById(tripId: Long): TripDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trip: TripDto)

    @Query("SELECT * FROM trips")
    fun loadAll(): Flow<List<TripDto>>

    @Update
    suspend fun update(trip: TripDto)
}
