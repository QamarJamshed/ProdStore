package com.example.wishlist_impl.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.wishlist_api.WishlistRoute
import com.example.wishlist_impl.presentation.WishlistScreen
import com.example.wishlist_impl.presentation.WishlistViewModel
import org.koin.androidx.compose.koinViewModel

fun EntryProviderScope<NavKey>.wishlistEntry(
    onProductClick: (Int) -> Unit,
    onNavigateToHome: () -> Unit
) {
    entry<WishlistRoute> {
        val viewModel: WishlistViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        WishlistScreen(
            state = state,
            onIntent = viewModel::onIntent,
            onProductClick = onProductClick,
            onNavigateToHome = onNavigateToHome
        )
    }
}
