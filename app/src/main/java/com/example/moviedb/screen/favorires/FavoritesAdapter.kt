package com.example.moviedb.screen.favorires

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemFavoriteBinding
import com.example.moviedb.screen.favorires.FavoritesAdapter.ViewHolder

class FavoritesAdapter(
    val onItemClick: (movie: Movie) -> Unit,
    val onUnFavoriesClick: (movie: Movie) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {

    private var _movies = mutableListOf<Movie>()

    private var _genres = mutableListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemFavoriteBinding = DataBindingUtil.inflate<ItemFavoriteBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_favorite,
            parent,
            false
        )
        return ViewHolder(itemFavoriteBinding, _genres, onItemClick, onUnFavoriesClick)
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

    fun deleteMovie(movie: Movie) {
        val index = _movies.indexOf(movie)
        _movies.removeAt(index)
        notifyItemRemoved(index)
    }

    class ViewHolder(
        val itemFavoriteBinding: ItemFavoriteBinding,
        val genres: List<Genre>,
        onItemClick: (movie: Movie) -> Unit,
        onUnFavoriesClick: (movie: Movie) -> Unit
    ) :
        RecyclerView.ViewHolder(itemFavoriteBinding.root) {

        init {
            itemFavoriteBinding.cardView.setOnClickListener {
                itemFavoriteBinding.movie?.let(onItemClick)
            }
            itemFavoriteBinding.imageViewFavories.setOnClickListener {
                itemFavoriteBinding.movie?.let(onUnFavoriesClick)
            }
        }

        fun bind(movie: Movie) {
            itemFavoriteBinding.movie = movie
            movie.genre_ids?.let {
                setGenres(itemFavoriteBinding.textViewMovieGenres, it, genres)
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
