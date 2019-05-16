package com.example.moviedb.utils

import android.view.View
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedb.data.model.Genre

object BindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(backdropPath: String?) {
        val cropOptions = RequestOptions().centerCrop()
        val builder = StringBuilder(Constant.BASE_URL_IMG)
        Glide.with(this.context)
            .applyDefaultRequestOptions(cropOptions)
            .load(builder.append(backdropPath).toString())
            .into(this)
    }

    @BindingAdapter("rating")
    @JvmStatic
    fun RatingBar.setRating(voteAverage: Double?) {
        voteAverage?.let {
            this.rating = it.toFloat() / 2
        }
    }

    @BindingAdapter("genresNames")
    @JvmStatic
    fun TextView.setGenres(genres: List<Genre>?) {
        genres?.let { genre ->
            val builder = StringBuilder()
            genre.forEach {
                builder.append("${it.name}, ")
            }
            this.text = builder.substring(0, builder.length - 2).toString()
        }
    }

    @BindingAdapter("setVisibility")
    @JvmStatic
    fun View.setVisibility(isVisible: MutableLiveData<Boolean>?) {
        isVisible?.value?.let {
            this.visibility = if (it) View.VISIBLE else View.GONE
        } ?: kotlin.run { this.visibility = View.GONE }
    }
}
