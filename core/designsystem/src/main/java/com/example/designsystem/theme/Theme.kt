package com.example.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.designsystem.theme.color.Background
import com.example.designsystem.theme.color.Primary
import com.example.designsystem.theme.color.Surface
import com.example.designsystem.theme.color.onSurface
import com.example.designsystem.theme.color.onSurfaceVariant
import com.example.designsystem.theme.typography.AppTypography

private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    background = Background,
    surface = Surface,
    onSurface = onSurface,
    onSurfaceVariant = onSurfaceVariant
)

private val DarkColors = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),
    onSurfaceVariant = onSurfaceVariant
)

@Composable
fun EcommerceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}