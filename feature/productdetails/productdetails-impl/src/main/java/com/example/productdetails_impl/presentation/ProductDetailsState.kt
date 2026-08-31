package com.example.productdetails_impl.presentation

import com.example.domain.model.Product

data class ProductDetailsState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
)
