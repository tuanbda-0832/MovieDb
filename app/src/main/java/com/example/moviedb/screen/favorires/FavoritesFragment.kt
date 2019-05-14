package com.example.moviedb.screen.favorires

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.FavoriesFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FavoritesViewModel>() {
    companion object {
        fun newInstance() = FavoritesFragment()
    }
    override val viewModel: FavoritesViewModel by viewModel()

    private lateinit var _favoriesFragmentBinding: FavoriesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favoriesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.favories_fragment, container, false)
        return _favoriesFragmentBinding.root
    }

    override fun setUpView() {
    }

    override fun bindView() {
    }
}
