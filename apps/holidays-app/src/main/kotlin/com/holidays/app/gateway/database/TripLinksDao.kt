package com.holidays.app.gateway.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.holidays.app.gateway.database.dto.TripLinksDto

@Dao
interface TripLinksDao {

    @Delete
    suspend fun delete(trip: TripLinksDto)

    @Query("SELECT * FROM trips_links WHERE trip_id = :tripId")
    suspend fun findById(tripId: Long): List<TripLinksDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trip: TripLinksDto)

    @Update
    suspend fun update(trip: TripLinksDto)
}
