package com.example.wishlist_impl.presentation

import com.example.domain.model.Product

data class WishlistState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
