package com.example.wishlist_impl.di

import com.example.wishlist_impl.presentation.WishlistViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val wishlistModule = module {
    viewModel { WishlistViewModel(get()) }
}
