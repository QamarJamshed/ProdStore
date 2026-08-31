package com.example.onboarding_impl.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OnboardingPager(
    pagerState: PagerState,
    imageUrls: List<String>
) {

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when(page) {
            0 -> {
                OnboardingPage(
                    title = "Discover Products",
                    description = "Explore wide range of products and find what you need",
                    imageUrl = imageUrls.getOrNull(0) ?: ""
                )
            }
            1 -> {
                OnboardingPage(
                    title = "Save your favourites",
                    description = "Keep your favourites products saved for easy access later",
                    imageUrl = imageUrls.getOrNull(1) ?: ""
                )
            }
        }
    }
}