package com.example.ecommerceapp

import android.app.Application
import com.example.data.di.dataModule
import com.example.database.database.databaseModule
import com.example.network.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                networkModule,
                databaseModule,
                dataModule
            )
        }
    }
}