package com.example.moviedb.utils.extensions

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.moviedb.data.model.Genre
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun View.showSnackBar(message: CharSequence) =
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()

fun TextView.setGenres(genresIds: List<Int>, genres: List<Genre>) {
    val builder = StringBuilder()
    genresIds.forEach {
        for (genre in genres) {
            if (genre.id == it) {
                builder.append("${genre.name}, ")
                break
            }
        }
    }
    this.text = builder.toString()
}
