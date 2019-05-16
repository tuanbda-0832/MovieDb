package com.example.moviedb.screen.favorires

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.base.BaseViewModel
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.HomeRepository
import com.example.moviedb.utils.liveData.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoritesViewModel(val homeRepository: HomeRepository) : BaseViewModel() {
    val genres: MutableLiveData<List<Genre>> = MutableLiveData()

    val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    val onMessageError = SingleLiveEvent<String>()

    fun getFavoriteMovies() {
        addDisposable(
            homeRepository.getFavorieMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { movies, error ->
                    this@FavoritesViewModel.movies.value = movies
                    error?.let {
                        movies
                    }
                }
        )
    }

    fun getGenresLocal() {
        addDisposable(
            homeRepository.getGenresLocal()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { genres, error ->
                    this@FavoritesViewModel.genres.value = genres
                }
        )
    }

    fun unFavoriteMovie(movie: Movie) {
        addDisposable(
            homeRepository.deleteMovie(movie)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { genres, error ->
                    when (error) {
                        null -> {
                            val oldMovies = movies.value ?: arrayListOf()
                            val movies = mutableListOf<Movie>().apply {
                                addAll(oldMovies)
                                remove(movie)
                            }
                            this.movies.value = movies
                        }
                        else -> onMessageError.value = error.localizedMessage
                    }
                }
        )
    }
}
