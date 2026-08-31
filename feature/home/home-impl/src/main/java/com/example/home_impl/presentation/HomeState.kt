package com.example.home_impl.presentation

import androidx.paging.PagingData
import com.example.domain.model.Category
import com.example.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val products: Flow<PagingData<Product>> = emptyFlow(),
    val categories: List<Category> = emptyList(),
    val selectedCategory: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
