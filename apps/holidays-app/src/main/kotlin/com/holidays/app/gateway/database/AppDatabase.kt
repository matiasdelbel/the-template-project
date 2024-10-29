package com.holidays.app.gateway.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.holidays.app.gateway.dto.PlaceDto
import com.holidays.app.gateway.dto.TripDto
import com.holidays.app.gateway.dto.TripLinksDto

@Database(
    entities = [
        PlaceDto::class,
        TripDto::class,
        TripLinksDto::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun placesDao(): PlacesDao

    abstract fun tripsDao(): TripDao

    abstract fun tripLinksDao(): TripLinksDao
}
