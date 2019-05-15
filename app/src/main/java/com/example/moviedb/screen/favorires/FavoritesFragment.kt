package com.example.moviedb.screen.favorires

import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.FavoriesFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FavoritesViewModel, FavoriesFragmentBinding>() {
    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override val viewModel: FavoritesViewModel by viewModel()

    override fun getLayout(): Int = R.layout.favories_fragment

    override fun setUpView() {
    }

    override fun bindView() {
    }
}
