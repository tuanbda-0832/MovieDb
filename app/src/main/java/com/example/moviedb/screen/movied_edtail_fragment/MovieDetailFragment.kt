package com.example.moviedb.screen.movied_edtail_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.MovieDetailFragmentBinding
import com.example.moviedb.utils.extensions.showToast
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

    private val _movieDetailViewModel: MovieDetailViewModel by viewModel()

    private var _onActionBarListener: OnActionBarListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnActionBarListener) {
            _onActionBarListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _movieDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        _movieDetailFragmentBinding.setLifecycleOwner(this)
        return _movieDetailFragmentBinding.root
    }

    override fun setUpView() {
        _onActionBarListener?.onSetUpActionBar(getString(R.string.text_movie_detail))
    }

    override fun bindView() {
        super.bindView()
        _movieDetailFragmentBinding.viewModel = _movieDetailViewModel
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

    override fun onDetach() {
        super.onDetach()
        _onActionBarListener?.onSetUpActionBar(getString(R.string.app_name), false)
        _onActionBarListener = null
    }

    interface OnActionBarListener {
        fun onSetUpActionBar(title: String, showBackArrow: Boolean = true)
    }
}
