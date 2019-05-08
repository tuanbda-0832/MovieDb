package com.example.moviedb.utils.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun View.showSnackBar(message: CharSequence) =
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
