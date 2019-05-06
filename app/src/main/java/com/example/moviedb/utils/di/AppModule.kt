package com.example.moviedb.utils.di

import com.example.moviedb.data.remote.api.AppApi
import com.example.moviedb.screen.home.HomeViewModel
import com.example.moviedb.utils.Constant
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofit(): Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constant.BASE_URL).build()

fun createAppApi(retrofit: Retrofit): AppApi =
    retrofit.create(AppApi::class.java)

val appModule = module {
    viewModel { HomeViewModel(get()) }
}

val networkModule = module {
    single(name = "retrofit") { createRetrofit() }
    single(name = "appApi") { createAppApi(get()) }
}


