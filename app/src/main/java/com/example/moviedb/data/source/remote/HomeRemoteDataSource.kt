package com.example.moviedb.data.source.remote

import com.example.moviedb.data.source.HomeDatasource
import com.example.moviedb.data.source.local.remote.response.PopularResponse
import com.example.moviedb.data.source.remote.api.AppApi
import com.example.moviedb.data.source.remote.response.GenresReponse
import io.reactivex.Single
import retrofit2.Response

class HomeRemoteDataSource(val appApi: AppApi) : HomeDatasource.Remote {
    override fun getGenres(): Single<Response<GenresReponse>> = appApi.getGenres()

    override fun getPopularMovies(id: Int): Single<Response<PopularResponse>> = appApi.getPopularMovies(id)
}
