package com.example.moviedb.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemHomeBinding

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var _movies = mutableListOf<Movie>()

    private var _genres = mutableListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val homeAdapterBinding = DataBindingUtil.inflate<ItemHomeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home,
            parent,
            false
        )
        return ViewHolder(homeAdapterBinding, _genres)
    }

    override fun getItemCount(): Int = _movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_movies.get(position))
    }

    fun addData(movies: List<Movie>) {
        _movies.run {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    fun addGenres(genres: List<Genre>) {
        _genres.run {
            clear()
            addAll(genres)
        }
    }

    class ViewHolder(val itemHomeBinding: ItemHomeBinding, val genres: List<Genre>) :
        RecyclerView.ViewHolder(itemHomeBinding.root) {

        fun bind(movie: Movie) {
            itemHomeBinding.movie = movie
            movie.genre_ids?.let {
                setGenres(itemHomeBinding.textViewMovieGenres, it, genres)
            }
        }

        fun setGenres(view: TextView, genresIds: List<Int>, genres: List<Genre>) {
            val builder = StringBuilder()
            genresIds.forEach {
                for (genre in genres) {
                    if (genre.id == it) {
                        builder.append("${genre.name}, ")
                        break
                    }
                }
            }
            view.text = builder.toString()
        }
    }
}
