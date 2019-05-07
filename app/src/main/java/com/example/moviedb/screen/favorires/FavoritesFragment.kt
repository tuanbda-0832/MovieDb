package com.example.moviedb.screen.favorires

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment

class FavoritesFragment : BaseFragment() {
    companion object {
        fun newInstance() = FavoritesFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favories_fragment, container, false)
    }

    override fun setUpView(view: View) {
    }

    override fun bindView() {
    }
}
