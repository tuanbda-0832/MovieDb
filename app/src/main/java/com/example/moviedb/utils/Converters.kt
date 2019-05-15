package com.example.moviedb.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromGenreIds(genres: List<Int>): String {
            return Gson().toJson(genres)
        }

        @TypeConverter
        @JvmStatic
        fun toGenreIds(string: String): List<Int> {
            return GsonBuilder().create().fromJson(string, Array<Int>::class.java).toList()
        }
    }
}
