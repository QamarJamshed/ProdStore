package com.example.productdetails_impl.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.productdetails_impl.presentation.components.*

@Composable
fun ProductDetailsScreen(
    state: ProductDetailsState,
    onIntent: (ProductDetailsIntent) -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            ProductDetailsTopBar(
                product = state.product,
                onBack = onBack,
                onWishlistClick = { product ->
                    onIntent(ProductDetailsIntent.ToggleWishlist(product))
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
                    Button(onClick = { onIntent(ProductDetailsIntent.Retry) }) {
                        Text(text = "Retry")
                    }
                }
            } else {
                state.product?.let { product ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        ProductDetailsImage(
                            imageUrl = product.thumbnail,
                            contentDescription = product.title
                        )

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            ProductDetailsHeader(
                                title = product.title,
                                category = product.category,
                                brand = product.brand
                            )

                            ProductDetailsPrice(
                                price = product.price,
                                discountPercentage = product.discountPercentage
                            )

                            ProductDetailsDescription(
                                description = product.description
                            )

                            ProductDetailsInfo(
                                rating = product.rating,
                                stock = product.stock
                            )
                        }
                    }
                }
            }
        }
    }
}
