package com.example.moviedb.screen.home

import android.view.View
import com.example.moviedb.R
import com.example.moviedb.base.BaseAdapter
import com.example.moviedb.base.BaseViewHolder
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemHomeBinding
import com.example.moviedb.utils.extensions.setGenres

class HomeAdapter(
    val onItemClick: (movie: Movie) -> Unit,
    val onFavoriesClick: (movie: Movie) -> Unit
) :
    BaseAdapter<Movie, ItemHomeBinding>(DiffCallBackMovie()) {

    private var genres = mutableListOf<Genre>()

    override fun getItemViewType(position: Int): Int = R.layout.item_home

    override fun onBindViewHolder(holder: BaseViewHolder<Movie>, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = holder.viewDataBinding as ItemHomeBinding
        binding.item?.let {
            it.genre_ids?.let {
                binding.textViewMovieGenres.setGenres(it, genres)
            }
            binding.imageViewFavories.visibility = when (it.favorite) {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }
    }

    override fun init(binding: ItemHomeBinding) {
        binding.cardView.setOnClickListener {
            binding.item?.let(onItemClick)
        }
        binding.imageViewFavories.setOnClickListener {
            binding.item?.let(onFavoriesClick)
        }
    }

    fun addGenres(genres: List<Genre>) {
        this.genres.run {
            clear()
            addAll(genres)
        }
    }
}
