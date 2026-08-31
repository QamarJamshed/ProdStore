package com.example.productdetails_impl.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.productdetails_api.ProductDetailsRoute
import com.example.productdetails_impl.presentation.ProductDetailsScreen
import com.example.productdetails_impl.presentation.ProductDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.productDetailsEntry(
    onBack: () -> Unit,
) {
    entry<ProductDetailsRoute> { route ->
        val viewModel: ProductDetailsViewModel = koinViewModel { parametersOf(route.productId) }
        val state by viewModel.state.collectAsStateWithLifecycle()

        ProductDetailsScreen(
            state = state,
            onIntent = viewModel::onIntent,
            onBack = onBack
        )
    }
}
