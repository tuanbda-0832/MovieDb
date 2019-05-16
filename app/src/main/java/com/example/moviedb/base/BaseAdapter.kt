package com.example.moviedb.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

open class BaseAdapter<T, D : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) :
    ListAdapter<T, BaseViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<D>(
            layoutInflater, viewType, parent, false
        )
        init(binding)
        return BaseViewHolder(binding)
    }

    open fun init(binding: D) {
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) = holder.bind(getItem(position))
}
