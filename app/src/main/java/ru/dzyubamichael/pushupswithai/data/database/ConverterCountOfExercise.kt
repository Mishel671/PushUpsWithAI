package ru.dzyubamichael.pushupswithai.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterCountOfExercise {
    @TypeConverter
    fun convertListToString(list: List<Int>?): String? {
        if (list == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun mapStringToList(value: String?): List<Int>? {
        if (value == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }
}