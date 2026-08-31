package com.example.home_impl.presentation

import com.example.domain.model.Product

sealed interface HomeIntent {
    data class SelectCategory(val category: String?) : HomeIntent
    data class OnProductClick(val productId: Int) : HomeIntent
    data class ToggleWishlist(val product: Product) : HomeIntent
}
