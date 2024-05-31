package com.nutrilog.app.utils

import androidx.room.TypeConverter
import com.nutrilog.app.utils.helpers.convertDateToString
import com.nutrilog.app.utils.helpers.convertStringToDate
import java.util.Date

class RoomConverters {
    @TypeConverter
    fun fromLocalDate(date: Date?): String? {
        return date?.convertDateToString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): Date? {
        return dateString?.convertStringToDate()
    }
}
