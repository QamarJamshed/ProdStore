package com.example.wishlist_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.components.ProductCard
import com.example.domain.model.Product

@Composable
fun WishlistProductGrid(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
    onWishlistClick: (Product) -> Unit,
    contentPadding: PaddingValues
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = contentPadding,
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = products,
            key = { product -> product.id }
        ) { product ->
            ProductCard(
                imageUrl = product.thumbnail,
                name = product.title,
                price = product.price,
                rating = product.rating,
                isWishlisted = true,
                onWishlistClick = { onWishlistClick(product) },
                onClick = { onProductClick(product.id) }
            )
        }
    }
}
