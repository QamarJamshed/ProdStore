package com.example.domain.repository

import com.example.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    fun getWishlist(): Flow<List<Product>>
    suspend fun toggleWishlist(product: Product)
    suspend fun isWishlisted(productId: Int): Boolean
}