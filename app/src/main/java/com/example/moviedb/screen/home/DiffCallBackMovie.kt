package com.example.moviedb.screen.home

import androidx.recyclerview.widget.DiffUtil
import com.example.moviedb.data.model.Movie

class DiffCallBackMovie : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}