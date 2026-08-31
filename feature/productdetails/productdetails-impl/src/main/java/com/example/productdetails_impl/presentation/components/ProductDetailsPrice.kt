package com.example.productdetails_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ProductDetailsPrice(
    price: Double,
    discountPercentage: Double,
) {
    val originalPrice = if (discountPercentage > 0) price / (1.0 - (discountPercentage / 100.0)) else null

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = "$${"%.2f".format(price)}",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        originalPrice?.let {
            Text(
                text = "$${"%.2f".format(it)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textDecoration = TextDecoration.LineThrough
            )
        }

        if (discountPercentage > 0) {
            Surface(
                color = Color(0xFFE4F3E7), // Subtle green
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "${"%.0f".format(discountPercentage)}% OFF",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF2E7D32), // Darker green
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}
