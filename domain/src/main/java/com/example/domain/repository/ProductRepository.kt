package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Category
import com.example.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductsPaged(category: String? = null): Flow<PagingData<Product>>
    suspend fun getCategories(): Result<List<Category>>
    suspend fun getProductById(id: Int): Result<Product>
    suspend fun getProducts(limit: Int): Result<List<Product>>
}