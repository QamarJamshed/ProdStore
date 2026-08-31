package com.example.productdetails_impl.presentation

import com.example.domain.model.Product

sealed interface ProductDetailsIntent {
    data class ToggleWishlist(val product: Product) : ProductDetailsIntent
    data object Retry : ProductDetailsIntent
}
