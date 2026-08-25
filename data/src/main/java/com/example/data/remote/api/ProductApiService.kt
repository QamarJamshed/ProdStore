package com.example.data.remote.api

import retrofit2.http.Query
import com.example.data.remote.dto.CategoryDto
import com.example.data.remote.dto.ProductDto
import com.example.data.remote.dto.ProductListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ) : ProductListResponseDto

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String,
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
        ): ProductListResponseDto

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDto

    @GET("products/categories")
    suspend fun getCategories() : List<CategoryDto>

}