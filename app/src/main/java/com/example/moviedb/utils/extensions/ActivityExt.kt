package com.example.moviedb.utils.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragmentToActivity(
    @IdRes containerId: Int, fragment: Fragment,
    addToBackStack: Boolean = true, tag: String = fragment::class.java.simpleName
) {
    supportFragmentManager.beginTransaction().run {
        if (addToBackStack) {
            addToBackStack(tag)
        }
        add(containerId, fragment, tag).commit()
    }
}
