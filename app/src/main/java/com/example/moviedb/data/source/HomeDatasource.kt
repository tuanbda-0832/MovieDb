package com.example.moviedb.data.source

import com.example.moviedb.data.source.local.remote.response.PopularResponse
import com.example.moviedb.data.source.remote.response.GenresReponse
import io.reactivex.Single
import retrofit2.Response

interface HomeDatasource {
    interface Local

    interface Remote {
        fun getPopularMovies(id: Int): Single<Response<PopularResponse>>

        fun getGenres(): Single<Response<GenresReponse>>
    }
}