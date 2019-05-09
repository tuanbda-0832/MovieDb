package com.example.moviedb.screen.movied_edtail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.MovieDetailFragmentBinding

class MovieDetailFragment : BaseFragment() {
    companion object {

        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var _movieDetailFragmentBinding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _movieDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        return _movieDetailFragmentBinding.root
    }

    override fun setUpView() {
    }

    override fun bindView() {
    }
}
