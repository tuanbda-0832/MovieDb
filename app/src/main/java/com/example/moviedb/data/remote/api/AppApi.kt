package com.example.moviedb.data.remote.api

import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.remote.response.PopularResponse
import com.example.moviedb.utils.Constant
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApi {
    @GET("movie/popular?api_key=${Constant.API_KEY}")
    fun getPopularMovies(@Query("page") page: Int): Single<Response<PopularResponse>>

    @GET("movie/{id}?api_key=${Constant.API_KEY}")
    fun getMovieDetails(@Path("id") id: Int): Single<Response<Movie>>
}
