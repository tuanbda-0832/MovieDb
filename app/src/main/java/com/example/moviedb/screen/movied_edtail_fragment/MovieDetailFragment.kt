package com.example.moviedb.screen.movied_edtail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.MovieDetailFragmentBinding
import com.example.moviedb.screen.home.HomeViewModel
import com.example.moviedb.utils.extensions.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment() {
    companion object {
        private const val ARG_ID = "ARG_ID"
        fun newInstance(id: Int): MovieDetailFragment = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }
    }

    private lateinit var _movieDetailFragmentBinding: MovieDetailFragmentBinding

    private val _homeViewModel: HomeViewModel by sharedViewModel()
    private val _movieDetailViewModel: MovieDetailViewModel by viewModel()

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

    override fun registerLiveData() {
        arguments?.let {
            _movieDetailViewModel.getMovieDetails(it.getInt(ARG_ID))
        }
        _movieDetailViewModel.onMessageError.observe(viewLifecycleOwner, Observer {
            it?.let {
                context?.showToast(it)
            }
        })
    }
}
