package com.example.database.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule  = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "ecommerce_database"
        ).build()
    }

    single { get<AppDatabase>().wishlistDao() }
}