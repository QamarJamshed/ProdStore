package com.example.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponseDto (
    val product: List<ProductDto>,
    val total: Int,
    val limit: Int,
    val skip: Int
)