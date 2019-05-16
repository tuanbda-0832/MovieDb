package com.example.moviedb.data.source.local

import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.source.HomeDatasource
import com.example.moviedb.data.source.local.database.AppDatabase
import io.reactivex.Single

class HomeLocalDataSource(val appDatabase: AppDatabase) :
    HomeDatasource.Local {

    override fun deleteMovie(movie: Movie): Single<Int> = appDatabase.movieDao().deleteMovie(movie)

    override fun insertMovie(movie: Movie): Single<Long> = appDatabase.movieDao().insertMovie(movie)

    override fun getFavorieMovies(): Single<MutableList<Movie>> = appDatabase.movieDao().getAll()

    override fun insertGenres(genes: List<Genre>): Single<List<Long>> = appDatabase.genreDao().insertAll(genes)

    override fun getGenresLocal(): Single<List<Genre>> = appDatabase.genreDao().getAll()
}
