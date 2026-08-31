package com.example.wishlist_impl.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.components.BottomNavBar
import com.example.wishlist_impl.presentation.components.WishlistEmptyState
import com.example.wishlist_impl.presentation.components.WishlistProductGrid
import com.example.wishlist_impl.presentation.components.WishlistTopBar

@Composable
fun WishlistScreen(
    state: WishlistState,
    onIntent: (WishlistIntent) -> Unit,
    onProductClick: (Int) -> Unit,
    onNavigateToHome: () -> Unit
) {
    Scaffold(
        topBar = { WishlistTopBar() },
        bottomBar = {
            BottomNavBar(
                selectedRoute = "wishlist",
                onHomeClick = onNavigateToHome,
                onWishlistClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp, vertical = 16.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            if (state.products.isEmpty() && !state.isLoading) {
                WishlistEmptyState(onExploreClick = onNavigateToHome)
            } else {
                WishlistProductGrid(
                    products = state.products,
                    onProductClick = onProductClick,
                    onWishlistClick = { product ->
                        onIntent(WishlistIntent.RemoveFromWishlist(product))
                    },
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 100.dp
                    )
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
            }
        }
    }
}
