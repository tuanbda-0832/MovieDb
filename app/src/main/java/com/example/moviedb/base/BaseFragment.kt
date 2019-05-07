package com.example.moviedb.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        bindView()
    }

    protected abstract fun setUpView(view: View)

    protected abstract fun bindView()
}
