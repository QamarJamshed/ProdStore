package com.example.productdetails_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun ProductDetailsImage(
    imageUrl: String,
    contentDescription: String?
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.2f)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentScale = ContentScale.Fit
    )
}
