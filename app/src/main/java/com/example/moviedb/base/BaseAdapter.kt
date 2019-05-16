package com.example.moviedb.base

import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.base.BaseAdapter.ViewHolder
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie

abstract class BaseAdapter<D : ViewDataBinding> :
    RecyclerView.Adapter<ViewHolder<D>>() {

    var movies = mutableListOf<Movie>()

    var genres = mutableListOf<Genre>()

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder<D>, position: Int) {
        holder.bind(movies.get(position))
    }

    fun addData(movies: List<Movie>) {
        this@BaseAdapter.movies.run {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    fun addLoadMoreData(movies: List<Movie>) {
        this@BaseAdapter.movies.run {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    fun addGenres(genres: List<Genre>) {
        this@BaseAdapter.genres.run {
            clear()
            addAll(genres)
        }
    }

    fun deleteMovie(movie: Movie) {
        val index = this@BaseAdapter.movies.indexOf(movie)
        this@BaseAdapter.movies.removeAt(index)
        notifyItemRemoved(index)
    }

    abstract class ViewHolder<D : ViewDataBinding>(
        protected val binding: D,
        protected val genres: List<Genre>
    ) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(movie: Movie)

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
