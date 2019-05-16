package com.example.moviedb.screen.favorires

import android.widget.TextView
import com.example.moviedb.R
import com.example.moviedb.base.BaseAdapter
import com.example.moviedb.base.BaseViewHolder
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemFavoriteBinding
import com.example.moviedb.screen.home.DiffCallBackMovie
import com.example.moviedb.utils.extensions.setGenres

class FavoritesAdapter(
    val onItemClick: (movie: Movie) -> Unit,
    val onUnFavoriesClick: (movie: Movie) -> Unit
) : BaseAdapter<Movie, ItemFavoriteBinding>(DiffCallBackMovie()) {

    var genres: List<Genre> = mutableListOf()

    override fun getItemViewType(position: Int): Int = R.layout.item_favorite

    override fun onBindViewHolder(holder: BaseViewHolder<Movie>, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = holder.viewDataBinding as ItemFavoriteBinding
        binding.item?.let {
            it.genre_ids?.let {
                binding.textViewMovieGenres.setGenres(it, genres)
            }
        }
    }

    override fun init(binding: ItemFavoriteBinding) {
        binding.cardView.setOnClickListener { binding.item?.let(onItemClick) }
        binding.imageViewFavories.setOnClickListener { binding.item?.let(onUnFavoriesClick) }
    }

    fun addGenres(genres: List<Genre>) {
        this.genres = genres
    }
}
