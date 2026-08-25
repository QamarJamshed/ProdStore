package com.example.data.di

import com.example.data.remote.api.ProductApiService
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.repository.WishlistRepositoryImpl
import com.example.domain.repository.ProductRepository
import com.example.domain.repository.WishlistRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<ProductApiService> {
        get<Retrofit>().create(ProductApiService::class.java)
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            api = get(),
            wishlistDao = get()
        )
    }

    single<WishlistRepository> {
        WishlistRepositoryImpl(
            dao = get()
        )
    }
}