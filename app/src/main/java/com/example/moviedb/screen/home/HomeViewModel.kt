package com.example.moviedb.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.base.BaseViewModel
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.HomeRepository
import com.example.moviedb.data.source.local.remote.error.RetrofitException
import com.example.moviedb.utils.liveData.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(val homeRepository: HomeRepository) : BaseViewModel() {

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val onMessageError = SingleLiveEvent<String>()

    val genres: MutableLiveData<List<Genre>> = MutableLiveData()

    val movie: MutableLiveData<Movie> = MutableLiveData()

    fun getPopularMovies(page: Int = 1) {
        addDisposable(
            homeRepository.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> movies.value = response.body()?.movies
                            false -> onMessageError.value = RetrofitException.toHttpError(response).getMessageError()
                        }
                    }
                    error?.let {
                        onMessageError.value = RetrofitException.toUnexpectedError(it).getMessageError()
                    }
                }
        )
    }

    fun getGenres() {
        addDisposable(
            homeRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> genres.value = it.body()?.genres
                            false -> onMessageError.value = RetrofitException.toHttpError(response).getMessageError()
                        }
                    }
                    error?.let {
                        onMessageError.value = RetrofitException.toUnexpectedError(it).getMessageError()
                    }
                }
        )
    }

    fun getMovieDetails(id: Int) {
        addDisposable(
            homeRepository.getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> movie.value = it.body()
                            false -> onMessageError.value = RetrofitException.toHttpError(response).getMessageError()
                        }
                    }
                    error?.let {
                        onMessageError.value = RetrofitException.toUnexpectedError(it).getMessageError()
                    }
                }
        )
    }
}
