package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Product


@Entity(tableName = "wishlist_items")
data class WishListEntity (
    @PrimaryKey
    val productId: Int,
    val title: String,
    val price: Double,
    val discountPercentage: Double,
    val thumbnail: String,
    val rating: Double,
    val brand: String?,
    val category: String
)

fun Product.toWishlistEntity(): WishListEntity {
    return WishListEntity(
        productId = id,
        title = title,
        price = price,
        discountPercentage = discountPercentage,
        thumbnail = thumbnail,
        rating = rating,
        brand = brand,
        category = category
    )
}


fun WishListEntity.toDomain(): Product {
    return Product(
        id = productId,
        title = title,
        description = "",
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = 0,
        brand = brand ?: "Unknown",
        category = category,
        thumbnail = thumbnail,
        images = listOf(thumbnail),
        isWishlisted = true
    )
}