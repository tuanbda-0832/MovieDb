package com.example.moviedb.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.base.BaseAdapter
import com.example.moviedb.data.model.Genre
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemHomeBinding

class HomeAdapter(
    val onItemClick: (movie: Movie) -> Unit,
    val onFavoriesClick: (movie: Movie) -> Unit
) : BaseAdapter<ItemHomeBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val homeAdapterBinding = DataBindingUtil.inflate<ItemHomeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home,
            parent,
            false
        )
        return ViewHolder(homeAdapterBinding, genres, onItemClick, onFavoriesClick)
    }

    class ViewHolder(
        itemHomeBinding: ItemHomeBinding,
        genres: List<Genre>,
        onItemClick: (movie: Movie) -> Unit,
        onFavoriesClick: (movie: Movie) -> Unit
    ) :
        BaseAdapter.ViewHolder<ItemHomeBinding>(itemHomeBinding, genres) {

        init {
            itemHomeBinding.cardView.setOnClickListener {
                itemHomeBinding.movie?.let(onItemClick)
            }
            itemHomeBinding.imageViewFavories.setOnClickListener {
                itemHomeBinding.movie?.let(onFavoriesClick)
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
