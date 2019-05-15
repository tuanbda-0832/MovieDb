package com.example.moviedb

import android.app.Application
import com.example.moviedb.utils.di.localModule
import com.example.moviedb.utils.di.networkModule
import com.example.moviedb.utils.di.repositoryModule
import com.example.moviedb.utils.di.viewModelModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            arrayListOf(
                viewModelModule,
                networkModule,
                repositoryModule,
                localModule
            )
        )
    }
}
