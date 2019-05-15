package com.example.moviedb.utils.di

import com.example.moviedb.data.repository.HomeRepository
import com.example.moviedb.data.source.HomeDatasource
import com.example.moviedb.data.source.local.HomeLocalDataSource
import com.example.moviedb.data.source.local.database.AppDatabase
import com.example.moviedb.data.source.remote.HomeRemoteDataSource
import com.example.moviedb.data.source.remote.api.AppApi
import org.koin.dsl.module.module

fun createHomeRemoteDataSource(appApi: AppApi): HomeDatasource.Remote = HomeRemoteDataSource(appApi)

fun createHomeLocalDataSource(appDatabase: AppDatabase): HomeDatasource.Local = HomeLocalDataSource(appDatabase)

fun createHomeRepository(
    homeRemoteDataSource: HomeDatasource.Remote,
    homeLocalDataSource: HomeDatasource.Local
): HomeRepository =
    HomeRepository(homeRemoteDataSource, homeLocalDataSource)

val repositoryModule = module {
    single { createHomeRepository(get(), get()) }
    single { createHomeRemoteDataSource(get()) }
    single { createHomeLocalDataSource(get()) }
}
