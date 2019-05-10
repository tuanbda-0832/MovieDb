package com.example.moviedb.utils.di

import com.example.moviedb.data.repository.HomeRepository
import com.example.moviedb.data.source.HomeDatasource
import com.example.moviedb.data.source.remote.HomeRemoteDataSource
import com.example.moviedb.data.source.remote.api.AppApi
import org.koin.dsl.module.module

fun createHomeRemoteDataSource(appApi: AppApi): HomeDatasource.Remote = HomeRemoteDataSource(appApi)

fun createHomeRepository(homeRemoteDataSource: HomeDatasource.Remote): HomeRepository =
    HomeRepository(homeRemoteDataSource)

val repositoryModule = module {
    single { createHomeRepository(get()) }
    single { createHomeRemoteDataSource(get()) }
}