package com.example.moviedb.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.source.local.dao.GenreDao
import com.example.moviedb.data.source.local.dao.MovieDao
import com.example.moviedb.utils.Converters

@Database(entities = [Movie::class, Genre::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
}
