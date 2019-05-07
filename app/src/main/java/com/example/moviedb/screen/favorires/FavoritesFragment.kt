package com.example.moviedb.screen.favorires

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.FavoriesFragmentBinding

class FavoritesFragment : BaseFragment() {
    companion object {
        fun newInstance() = FavoritesFragment()
    }

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
