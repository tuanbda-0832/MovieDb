package com.example.moviedb.screen.home

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.base.BaseViewModel
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.remote.api.AppApi
import com.example.moviedb.data.remote.error.RetrofitException
import com.example.moviedb.utils.liveData.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(val appApi: AppApi) : BaseViewModel() {
    val movies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    val onMessageError by lazy {
        SingleLiveEvent<String>()
    }

    fun getPopularMovies(page: Int = 1) {
        addDisposable(
            appApi.getPopularMovies(page)
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
}
