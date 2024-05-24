package com.tracking.app.gateway.dto

import androidx.room.ColumnInfo
import com.tracking.app.model.TimePeriod

data class TimePeriodDto(
    @ColumnInfo(name = "minutes") val minutes: Int,
    @ColumnInfo(name = "seconds") val seconds: Int,
) {

    fun toTimePeriod() = TimePeriod(minutes, seconds)
}
