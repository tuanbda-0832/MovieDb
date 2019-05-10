package com.example.moviedb.utils.di

import com.example.moviedb.screen.home.HomeViewModel
import com.example.moviedb.screen.movied_edtail_fragment.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}






