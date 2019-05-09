package com.example.moviedb

import android.app.Application
import com.example.moviedb.utils.di.appModule
import com.example.moviedb.utils.di.datasourceModule
import com.example.moviedb.utils.di.networkModule
import com.example.moviedb.utils.di.repositoryModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            arrayListOf(
                appModule,
                networkModule,
                repositoryModule,
                datasourceModule
            )
        )
    }
}
