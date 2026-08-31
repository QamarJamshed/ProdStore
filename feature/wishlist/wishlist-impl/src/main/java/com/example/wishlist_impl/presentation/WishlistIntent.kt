package com.example.wishlist_impl.presentation

import com.example.domain.model.Product

sealed interface WishlistIntent {
    data class RemoveFromWishlist(val product: Product) : WishlistIntent
}
