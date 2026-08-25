package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.remote.api.ProductApiService
import com.example.data.remote.dto.toDomain
import com.example.data.remote.paging.ProductPagingSource
import com.example.database.dao.WishListDao
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val api: ProductApiService,
    private val wishlistDao: WishListDao
): ProductRepository {
    override fun getProductsPaged(category: String?): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),

            pagingSourceFactory = { ProductPagingSource(api, category) }
        ).flow.map { pagingData ->
            pagingData.map { product ->
                product.copy(isWishlisted = wishlistDao.isWishlisted(product.id))
            }
        }
    }

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val categories = api.getCategories().map { it.toDomain() }
            Result.success(categories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProductById(id: Int): Result<Product> {
        return try {
            val isWishlisted = wishlistDao.isWishlisted(id)
            val product = api.getProductById(id).toDomain(isWishlisted)
            Result.success(product)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}