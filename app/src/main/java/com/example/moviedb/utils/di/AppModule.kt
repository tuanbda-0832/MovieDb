package com.example.moviedb.utils.di

import com.example.moviedb.data.remote.api.AppApi
import com.example.moviedb.screen.home.HomeViewModel
import com.example.moviedb.utils.Constant
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY_PARAM = "api_key"

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .baseUrl(Constant.BASE_URL).build()

fun createAppApi(retrofit: Retrofit): AppApi =
    retrofit.create(AppApi::class.java)

fun createOkHttpClient(): OkHttpClient {
    val client = OkHttpClient().newBuilder()
    client.interceptors()
        .add(Interceptor { chain ->
            var request = chain.request()
            val url = request
                .url()
                .newBuilder()
                .addQueryParameter(API_KEY_PARAM, Constant.API_KEY_VALUE).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })
    return client.build()
}

val appModule = module {
    viewModel { HomeViewModel(get()) }
}

val networkModule = module {
    single { createRetrofit(get()) }
    single { createAppApi(get()) }
    single { createOkHttpClient() }
}
