package com.example.home_impl.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.home_api.HomeRoute
import com.example.home_impl.presentation.HomeIntent
import com.example.home_impl.presentation.HomeScreen
import com.example.home_impl.presentation.HomeViewModel
import org.koin.androidx.compose.koinViewModel

fun EntryProviderScope<NavKey>.homeEntry(
    onNavigateToProductDetails: (Int) -> Unit,
    onNavigateToWishlist: () -> Unit
) {
    entry<HomeRoute> {
        val viewModel: HomeViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        HomeScreen(
            state = state,
            onIntent = { intent ->
                when (intent) {
                    is HomeIntent.OnProductClick -> onNavigateToProductDetails(intent.productId)
                    else -> viewModel.onIntent(intent)
                }
            },
            onNavigateToWishlist = onNavigateToWishlist
        )
    }
}
