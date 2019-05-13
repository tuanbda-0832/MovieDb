package com.example.moviedb.screen.movied_edtail_fragment

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.base.BaseViewModel
import com.example.moviedb.data.model.MovieDetail
import com.example.moviedb.data.repository.HomeRepository
import com.example.moviedb.data.source.local.remote.error.RetrofitException
import com.example.moviedb.utils.liveData.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(val homeRepository: HomeRepository) : BaseViewModel() {
    val movieDetail: MutableLiveData<MovieDetail> = MutableLiveData()
    val onMessageError = SingleLiveEvent<String>()

    fun getMovieDetails(id: Int) {
        addDisposable(
            homeRepository.getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> movieDetail.value = it.body()
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
