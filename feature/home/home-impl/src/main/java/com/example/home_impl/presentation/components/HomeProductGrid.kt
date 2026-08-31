package com.example.home_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.designsystem.components.ProductCard
import com.example.domain.model.Product

@Composable
fun HomeProductGrid(
    pagingItems: LazyPagingItems<Product>,
    onProductClick: (Int) -> Unit,
    onWishlistClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val isRefreshLoading = pagingItems.loadState.refresh is LoadState.Loading

    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = contentPadding,
        modifier = modifier.fillMaxSize()
    ) {
        if (isRefreshLoading) {
            items(8) {
                ProductCardShimmer()
            }
        } else {
            items(
                count = pagingItems.itemCount,
                key = { index -> pagingItems[index]?.id ?: index }
            ) { index ->
                pagingItems[index]?.let { product ->
                    ProductCard(
                        imageUrl = product.thumbnail,
                        name = product.title,
                        price = product.price,
                        rating = product.rating,
                        isWishlisted = product.isWishlisted,
                        onWishlistClick = { onWishlistClick(product) },
                        onClick = { onProductClick(product.id) }
                    )
                }
            }
        }
    }
}
