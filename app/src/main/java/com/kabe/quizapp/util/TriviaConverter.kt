package com.kabe.quizapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson

class TriviaConverter {
    @TypeConverter
    fun fromStringList(value: List<String>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<String> {
        return try {
            Gson().fromJson(value) //using extension function
        } catch (e: Exception) {
            listOf()
        }
    }
}