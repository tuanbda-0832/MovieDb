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

    val onProgessBarRecyclerView = SingleLiveEvent<Boolean>()

    val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    val moviesLocal: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    val onMessageError = SingleLiveEvent<String>()

    val onMessageSuccess = SingleLiveEvent<Boolean>()

    val onProgressBarEvent = SingleLiveEvent<Boolean>()

    val genres: MutableLiveData<List<Genre>> = MutableLiveData()

    fun getPopularMovies(page: Int = 1, isRefresh: Boolean = false) {
        if (page == 1 && !isRefresh) {
            onProgessBarRecyclerView.value = true
        }
        addDisposable(
            homeRepository.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    onProgessBarRecyclerView.value = false
                    onProgressBarEvent.value = false
                    response?.let {
                        when (it.isSuccessful) {
                            true -> {
                                val local = moviesLocal.value ?: mutableListOf()
                                val remote: MutableList<Movie>
                                if (page == 1) {
                                    remote = it.body()?.movies ?: mutableListOf()
                                } else {
                                    val oldMovies = this.movies.value ?: mutableListOf()
                                    val newMovies = response.body()?.movies ?: mutableListOf()
                                    remote = mutableListOf<Movie>().apply {
                                        addAll(oldMovies)
                                        addAll(newMovies)
                                    }
                                }

                                for (movie in remote) {
                                    local.forEach {
                                        if (movie.id == it.id) movie.favorite = true
                                    }
                                }
                                movies.value = remote
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
        getPopularMovies(isRefresh = true)
    }

    fun getGenresRemote() {
        addDisposable(
            homeRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> {
                                val genres = it.body()?.genres ?: arrayListOf()
                                this@HomeViewModel.genres.value = genres
                                insertGenres(genres)
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

    fun insertGenres(genres: List<Genre>) {
        addDisposable(
            homeRepository.insertGenres(genres)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { ids, error ->
                    error?.let { onMessageError.value = it.localizedMessage }
                }
        )
    }

    fun getGenresLocal() {
        addDisposable(
            homeRepository.getGenresLocal()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { genres, error ->
                    if (genres.isEmpty() || error != null) getGenresRemote()
                    else this@HomeViewModel.genres.value = genres
                }
        )
    }

    fun getFavoriteMovies() {
        addDisposable(
            homeRepository.getFavorieMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { movies, error ->
                    moviesLocal.value = movies
                    error?.let {
                        onMessageError.value = it.localizedMessage
                    }
                }
        )
    }

    fun addFavorite(movie: Movie) {
        val newMovies: MutableList<Movie> = mutableListOf()
        movies.value?.let {
            newMovies.addAll(it)
        }
        val index = newMovies.indexOf(movie)
        val newMovie = newMovies[index].copy(favorite = true)
        newMovies[index] = newMovie
        movies.value = newMovies
        addDisposable(
            homeRepository.insertMovie(movie)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { row, error ->
                    onMessageSuccess.value = error == null
                }
        )
    }
}
