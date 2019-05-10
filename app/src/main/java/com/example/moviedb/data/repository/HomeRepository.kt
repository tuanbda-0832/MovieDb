package com.example.moviedb.data.repository

import com.example.moviedb.data.source.HomeDatasource
import com.example.moviedb.data.source.local.remote.response.PopularResponse
import com.example.moviedb.data.source.remote.response.GenresReponse
import io.reactivex.Single
import retrofit2.Response

class HomeRepository(
    val homeRemoteDataSource: HomeDatasource.Remote
) : HomeDatasource.Remote, HomeDatasource.Local {

    override fun getGenres(): Single<Response<GenresReponse>> = homeRemoteDataSource.getGenres()

    override fun getPopularMovies(id: Int): Single<Response<PopularResponse>> =
        homeRemoteDataSource.getPopularMovies(id)
}
