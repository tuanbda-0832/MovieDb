package com.example.moviedb.data.repository

import androidx.lifecycle.LiveData
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieDetail
import com.example.moviedb.data.source.HomeDatasource
import com.example.moviedb.data.source.remote.response.GenresReponse
import com.example.moviedb.data.source.remote.response.PopularResponse
import io.reactivex.Single
import retrofit2.Response

class HomeRepository(
    val homeRemoteDataSource: HomeDatasource.Remote,
    val homeLocalDataSource: HomeDatasource.Local
) : HomeDatasource.Remote, HomeDatasource.Local {

    override fun insertMovie(movie: Movie) {
        homeLocalDataSource.insertMovie(movie)
    }

    override fun getFavorieMovies(): Single<List<Movie>> = homeLocalDataSource.getFavorieMovies()

    override fun insertGenres(genes: List<Genre>): Single<List<Long>> = homeLocalDataSource.insertGenres(genes)

    override fun getGenresLocal(): Single<List<Genre>> = homeLocalDataSource.getGenresLocal()

    override fun getMovieDetails(id: Int): Single<Response<MovieDetail>> = homeRemoteDataSource.getMovieDetails(id)

    override fun getGenres(): Single<Response<GenresReponse>> = homeRemoteDataSource.getGenres()

    override fun getPopularMovies(page: Int): Single<Response<PopularResponse>> =
        homeRemoteDataSource.getPopularMovies(page)
}
