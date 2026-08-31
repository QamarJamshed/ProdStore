package com.example.ecommerceapp

import android.app.Application
import com.example.data.di.dataModule
import com.example.database.database.databaseModule
import com.example.network.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

import com.example.onboarding_impl.di.onboardingModule
import com.example.home_impl.di.homeModule
import com.example.productdetails_impl.di.productDetailsModule
import com.example.wishlist_impl.di.wishlistModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                databaseModule,
                dataModule,
                onboardingModule,
                homeModule,
                productDetailsModule,
                wishlistModule,
            )
        }
    }
}