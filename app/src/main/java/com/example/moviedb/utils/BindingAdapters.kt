package com.example.moviedb.utils

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedb.data.model.Genre

object BindingAdapters {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, backdropPath: String?) {
        val cropOptions = RequestOptions().centerCrop()
        val builder = StringBuilder(Constant.BASE_URL_IMG)
        Glide.with(view.context)
            .applyDefaultRequestOptions(cropOptions)
            .load(builder.append(backdropPath).toString())
            .into(view)
    }

    @BindingAdapter("app:rating")
    @JvmStatic
    fun setRating(ratingBar: RatingBar, voteAverage: Double?) {
        voteAverage?.let {
            ratingBar.rating = it.toFloat() / 2
        }
    }

    @BindingAdapter("app:genresNames")
    @JvmStatic
    fun setGenres(view: TextView, genres: List<Genre>?) {
        genres?.let {
            val builder = StringBuilder()
            it.forEach {
                builder.append("${it.name}, ")
            }
            view.text = builder.substring(0, builder.length - 2).toString()
        }
    }
}
