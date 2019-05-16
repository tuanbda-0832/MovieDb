package com.example.moviedb.data.source

import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieDetail
import com.example.moviedb.data.source.remote.response.GenresReponse
import com.example.moviedb.data.source.remote.response.PopularResponse
import io.reactivex.Single
import retrofit2.Response

interface HomeDatasource {
    interface Local {
        fun insertMovie(movie: Movie): Single<Long>

        fun getFavorieMovies(): Single<List<Movie>>

        fun insertGenres(genes: List<Genre>): Single<List<Long>>

        fun getGenresLocal(): Single<List<Genre>>

        fun deleteMovie(movie: Movie): Single<Int>
    }

    interface Remote {
        fun getPopularMovies(page: Int): Single<Response<PopularResponse>>

        fun getGenres(): Single<Response<GenresReponse>>

        fun getMovieDetails(id: Int): Single<Response<MovieDetail>>
    }
}
