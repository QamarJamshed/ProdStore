package com.example.data.repository

import com.example.database.dao.WishListDao
import com.example.database.entity.toDomain
import com.example.database.entity.toWishlistEntity
import com.example.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.domain.repository.WishlistRepository

class WishlistRepositoryImpl(
    private val dao: WishListDao
): WishlistRepository {
    override fun getWishlist(): Flow<List<Product>> {
        return dao.getAllWishlistItems().map { entities ->
            entities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun toggleWishlist(product: Product) {
        val alreadyWishlisted = dao.isWishlisted(product.id)
        if(alreadyWishlisted) {
            dao.deleteById(product.id)
        } else {
            dao.insert(product.toWishlistEntity())
        }
    }

    override suspend fun isWishlisted(productId: Int): Boolean {
        return dao.isWishlisted(productId)
    }

}