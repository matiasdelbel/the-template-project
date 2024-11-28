package com.tracking.app.gateway.database.dto

import androidx.room.ColumnInfo
import com.tracking.app.model.TimePeriod

data class TimePeriodDto(
    @ColumnInfo(name = "minutes") val minutes: Int,
    @ColumnInfo(name = "seconds") val seconds: Int,
) {

    fun toTimePeriod() = TimePeriod(minutes, seconds)
}

fun TimePeriod.toTimePeriodDto() = TimePeriodDto(minutes, seconds)