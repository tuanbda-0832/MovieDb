package com.example.moviedb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.moviedb.BR

abstract class BaseFragment<T : BaseViewModel, D : ViewDataBinding> : Fragment() {

    var binding: D? = null

    abstract val viewModel: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            this.setVariable(BR.viewModel, viewModel)
        }
        setUpView()
        bindView()
    }

    protected abstract fun getLayout(): Int

    protected abstract fun setUpView()

    open protected fun bindView() {
        registerLiveData()
    }

    open protected fun registerLiveData() {
    }
}
