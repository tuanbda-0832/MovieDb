package com.example.moviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, backdropPath: String?) {
        val cropOptions = RequestOptions().centerCrop()
        val builder = StringBuilder(Constant.BASE_URL_IMG)
        Glide.with(view.context)
            .applyDefaultRequestOptions(cropOptions)
            .load(builder.append(backdropPath).toString())
            .into(view);
    }
}
