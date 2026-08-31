package com.example.productdetails_api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsRoute(val productId: Int) : NavKey