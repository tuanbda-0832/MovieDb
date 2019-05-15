package com.example.moviedb.screen.movied_edtail_fragment

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.MovieDetailFragmentBinding
import com.example.moviedb.utils.extensions.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment<MovieDetailViewModel, MovieDetailFragmentBinding>() {

    companion object {
        private const val ARG_ID = "ARG_ID"
        fun newInstance(id: Int): MovieDetailFragment = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }
    }

    override val viewModel: MovieDetailViewModel by viewModel()

    private var _onActionBarListener: OnActionBarListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnActionBarListener) {
            _onActionBarListener = context
        }
    }

    override fun setUpView() {
        _onActionBarListener?.onSetUpActionBar(getString(R.string.text_movie_detail))
    }

    override fun getLayout(): Int = R.layout.movie_detail_fragment

    override fun registerLiveData() {
        arguments?.let {
            viewModel.getMovieDetails(it.getInt(ARG_ID))
        }
        viewModel.onMessageError.observe(viewLifecycleOwner, Observer {
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
