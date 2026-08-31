package com.example.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api.ProductApiService
import com.example.data.remote.dto.toDomain
import com.example.database.dao.WishListDao
import com.example.domain.model.Product

class ProductPagingSource(
    private  val api: ProductApiService,
    private val wishlistDao: WishListDao,
    private val category: String?
): PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val skip = params.key ?: 0
        val limit = params.loadSize

        return try {
            val response = if (category.isNullOrBlank()) {
                api.getProducts(limit = limit, skip = skip)
            } else {
                api.getProductsByCategory(category = category, limit = limit, skip = skip)
            }

            val products = response.products.map { 
                it.toDomain(isWishlisted = wishlistDao.isWishlisted(it.id))
            }

            LoadResult.Page(
                data = products,
                prevKey = null,
                nextKey = if (skip + limit >= response.total) null else skip + limit
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}