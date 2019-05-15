package com.example.moviedb.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.SearchFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchViewModel, SearchFragmentBinding>() {
    companion object {

        fun newInstance() = SearchFragment()
    }

    override fun getLayout(): Int = R.layout.search_fragment

    override val viewModel: SearchViewModel by viewModel()

    override fun setUpView() {
    }

    override fun bindView() {
    }
}
