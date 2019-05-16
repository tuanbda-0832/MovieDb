package com.example.moviedb.screen.favorires

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.moviedb.R
import com.example.moviedb.base.BaseAdapter
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemFavoriteBinding

class FavoritesAdapter(
    val onItemClick: (movie: Movie) -> Unit,
    val onUnFavoriesClick: (movie: Movie) -> Unit
) :
    BaseAdapter<ItemFavoriteBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemFavoriteBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_favorite,
            parent,
            false
        )
        return ViewHolder(binding, genres, onItemClick, onUnFavoriesClick)
    }

    class ViewHolder(
        binding: ItemFavoriteBinding, genres: List<Genre>,
        val onItemClick: (movie: Movie) -> Unit,
        val onUnFavoriesClick: (movie: Movie) -> Unit
    ) :
        BaseAdapter.ViewHolder<ItemFavoriteBinding>(binding, genres) {

        init {
            binding.cardView.setOnClickListener {
                binding.movie?.let(onItemClick)
            }
            binding.imageViewFavories.setOnClickListener {
                binding.movie?.let(onUnFavoriesClick)
            }
        }

        override fun bind(movie: Movie) {
            binding.movie = movie
            movie.genre_ids?.let {
                setGenres(binding.textViewMovieGenres, it, genres)
            }
        }
    }
}
