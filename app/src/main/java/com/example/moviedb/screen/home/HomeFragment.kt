package com.example.moviedb.screen.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.HomeFragmentBinding
import com.example.moviedb.utils.extensions.showToast
import com.example.moviedb.utils.liveData.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var _homeFragmentBinding: HomeFragmentBinding

    private val _homeViewModel: HomeViewModel by viewModel()
    private var _homeAdapter by autoCleared<HomeAdapter>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return _homeFragmentBinding.root
    }

    override fun setUpView() {
        _homeAdapter = HomeAdapter()
        _homeFragmentBinding.run {
            recyclerViewHome.run {
                adapter = _homeAdapter
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
            }
        }
    }

    override fun bindView() {
        registerLiveData()
    }

    private fun registerLiveData() {
        _homeViewModel.getPopularMovies()
        _homeViewModel.getGenres()
        _homeViewModel.movies.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addData(it)
        })
        _homeViewModel.genres.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addGenres(it)
        })
        _homeViewModel.onMessageError.observe(viewLifecycleOwner, Observer {
            it?.let {
                context?.showToast(it)
            }
        })
    }
}
