package com.example.productdetails_impl.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.designsystem.theme.color.HeartRed
import com.example.domain.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsTopBar(
    product: Product?,
    onBack: () -> Unit,
    onWishlistClick: (Product) -> Unit
) {
    TopAppBar(
        title = { Text(text = "Product Details") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            product?.let {
                IconButton(onClick = { onWishlistClick(it) }) {
                    Icon(
                        imageVector = if (it.isWishlisted) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (it.isWishlisted) "Remove from Wishlist" else "Add to Wishlist",
                        tint = if (it.isWishlisted) HeartRed else LocalContentColor.current
                    )
                }
            }
        }
    )
}
