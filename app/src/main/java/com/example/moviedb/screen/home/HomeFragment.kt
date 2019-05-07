package com.example.moviedb.screen.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.HomeFragmentBinding
import com.example.moviedb.utils.liveData.autoCleared
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var _homeFragmentBinding: HomeFragmentBinding

    private val _homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return _homeFragmentBinding.root
    }

    override fun setUpView() {
    }

    override fun bindView() {
    }
}
