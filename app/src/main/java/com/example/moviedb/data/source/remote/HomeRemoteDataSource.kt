package com.example.moviedb.data.source.remote

import com.example.moviedb.data.model.MovieDetail
import com.example.moviedb.data.source.HomeDatasource
import com.example.moviedb.data.source.remote.api.AppApi
import com.example.moviedb.data.source.remote.response.GenresReponse
import com.example.moviedb.data.source.remote.response.PopularResponse
import io.reactivex.Single
import retrofit2.Response

class HomeRemoteDataSource(val appApi: AppApi) : HomeDatasource.Remote {
    override fun getMovieDetails(id: Int): Single<Response<MovieDetail>> = appApi.getMovieDetails(id)


    override fun getGenres(): Single<Response<GenresReponse>> = appApi.getGenres()

    override fun getPopularMovies(page: Int): Single<Response<PopularResponse>> = appApi.getPopularMovies(page)
}
