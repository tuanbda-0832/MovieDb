package com.example.moviedb.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moviedb.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): Single<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMovie(movie: Movie): Single<Long>

    @Update
    fun updateMovie(movie: Movie): Single<Int>

    @Query("DELETE FROM movie")
    fun deleteTable(): Single<Int>

    @Delete
    fun deleteMovie(movie: Movie): Single<Int>
}
