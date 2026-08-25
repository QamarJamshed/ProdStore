package com.example.ecommerceapp.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Onboarding

@Serializable
data object Home

@Serializable
data object Wishlist

@Serializable
data class ProductDetail(val productId: Int)