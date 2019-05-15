package com.example.moviedb.data.repository

import com.example.moviedb.data.model.MovieDetail
import com.example.moviedb.data.source.HomeDatasource
import com.example.moviedb.data.source.remote.response.PopularResponse
import com.example.moviedb.data.source.remote.response.GenresReponse
import io.reactivex.Single
import retrofit2.Response

class HomeRepository(
    val homeRemoteDataSource: HomeDatasource.Remote
) : HomeDatasource.Remote, HomeDatasource.Local {

    override fun getMovieDetails(id: Int): Single<Response<MovieDetail>> = homeRemoteDataSource.getMovieDetails(id)

    override fun getGenres(): Single<Response<GenresReponse>> = homeRemoteDataSource.getGenres()

    override fun getPopularMovies(page: Int): Single<Response<PopularResponse>> =
        homeRemoteDataSource.getPopularMovies(page)
}
