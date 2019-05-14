package com.example.moviedb.screen.home

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
    val moviesLoadMore: MutableLiveData<List<Movie>> = MutableLiveData()

    val onMessageError = SingleLiveEvent<String>()
    val onProgressBarEvent = SingleLiveEvent<Boolean>()

    val genres: MutableLiveData<List<Genre>> = MutableLiveData()

    fun getPopularMovies(page: Int = 1) {
        addDisposable(
            homeRepository.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> {
                                if (page == 1) {
                                    movies.value = it.body()?.movies ?: arrayListOf()
                                } else {
                                    moviesLoadMore.value = response.body()?.movies
                                    onProgressBarEvent.value = false
                                }
                            }
                            false -> onMessageError.value = RetrofitException.toHttpError(response).getMessageError()
                        }
                    }
                    error?.let {
                        onMessageError.value = RetrofitException.toUnexpectedError(it).getMessageError()
                    }
                }
        )
    }

    fun onLoadMore(page: Int) {
        onProgressBarEvent.value = true
        getPopularMovies(page)
    }

    fun onRefresh() {
        getPopularMovies()
    }

    fun getGenres() {
        addDisposable(
            homeRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> genres.value = it.body()?.genres ?: arrayListOf()
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
