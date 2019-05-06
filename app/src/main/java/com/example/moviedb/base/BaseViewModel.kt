package com.example.moviedb.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val _compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        _compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        _compositeDisposable.clear()
    }
}
