package com.example.data.remote.dto

import com.example.domain.model.Category
import com.example.domain.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto (
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String? = null,
    val category: String,
    val thumbnail: String,
    val images: List<String> = emptyList()
)

fun ProductDto.toDomain(isWishlisted: Boolean= false): Product {
    return Product (
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand ?: "Unknown",
        category = category,
        thumbnail = thumbnail,
        images = images,
        isWishlisted = isWishlisted
    )
}