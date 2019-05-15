package com.example.moviedb.utils.di

import android.content.Context
import androidx.room.Room
import com.example.moviedb.data.source.local.database.AppDatabase
import com.example.moviedb.utils.Constant.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

fun createDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
}

val localModule = module {
    single { createDatabase(androidContext()) }
}
