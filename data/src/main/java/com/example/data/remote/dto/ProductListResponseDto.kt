package com.example.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponseDto (
    @SerialName("products")
    val products: List<ProductDto>,
    val total: Int,
    val limit: Int,
    val skip: Int
)