package com.example.moviedb.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedb.data.model.Genre
import io.reactivex.Single

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getAll(): Single<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(genre: List<Genre>): Single<List<Long>>

    @Query("DELETE FROM genre")
    fun deleteTable()
}
