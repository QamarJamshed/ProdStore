package com.example.home_impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.designsystem.components.AppSearchBar
import com.example.designsystem.components.BottomNavBar
import com.example.home_impl.presentation.components.HomeCategories
import com.example.home_impl.presentation.components.HomeProductGrid

@Composable
fun HomeScreen(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit,
    onNavigateToWishlist: () -> Unit,
//    onNavigateToProductDetails: (String) -> Unit
) {
    val pagingItems = state.products.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                selectedRoute = "home",
                onHomeClick = {  },
                onWishlistClick = onNavigateToWishlist,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp, vertical = 16.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "ProdStore",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppSearchBar(
                query = "",
                onQueryChange = {  },
                placeholder = "Search products"
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeCategories(
                categories = state.categories,
                selectedCategory = state.selectedCategory,
                onCategorySelected = { category ->
                    onIntent(HomeIntent.SelectCategory(category))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeProductGrid(
                pagingItems = pagingItems,
                onProductClick = { productId ->
                    onIntent(HomeIntent.OnProductClick(productId))
//                    onNavigateToProductDetails(productId)
                },
                onWishlistClick = { product ->
                    onIntent(HomeIntent.ToggleWishlist(product))
                },
                contentPadding = PaddingValues(bottom = 100.dp),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
