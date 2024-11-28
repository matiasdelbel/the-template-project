package com.tracking.app.gateway.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tracking.app.gateway.database.dto.RunningDto

@Database(
    entities = [RunningDto::class],
    version = 1
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao

}
