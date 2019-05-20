package com.example.moviedb.utils.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moviedb.data.source.local.database.AppDatabase
import com.example.moviedb.utils.Constant.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Since we didn't alter the table, there's nothing else to do here.
    }
}

fun createDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).addMigrations(MIGRATION_1_2).build()
}

val localModule = module {
    single { createDatabase(androidContext()) }
}
