package com.holidays.app.gateway.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.holidays.app.gateway.database.dto.PlaceDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PlacesDao {

    @Delete
    suspend fun delete(place: PlaceDto)

    @Query("SELECT * FROM places WHERE id = :placeId")
    suspend fun findById(placeId: Long): PlaceDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(place: PlaceDto)

    @Query("SELECT * FROM places")
    fun loadAll(): Flow<List<PlaceDto>>

    @Update
    suspend fun update(place: PlaceDto)
}
