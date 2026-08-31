package com.example.productdetails_impl.di

import com.example.productdetails_impl.presentation.ProductDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val productDetailsModule = module {
    viewModel { (productId: Int) ->
        ProductDetailsViewModel(productId, get(), get())
    }
}
