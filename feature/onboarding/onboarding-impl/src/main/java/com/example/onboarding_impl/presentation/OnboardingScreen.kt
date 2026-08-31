package com.example.onboarding_impl.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.onboarding_impl.presentation.components.GetStartedButton
import com.example.onboarding_impl.presentation.components.OnboardingIndicator
import com.example.onboarding_impl.presentation.components.OnboardingPager
import com.example.onboarding_impl.presentation.components.SkipButton

@Composable
fun OnboardingScreen(
    state: OnboardingState,
    onNavigateToHome: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { state.totalPage }
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .safeDrawingPadding()
        ) {
            OnboardingPager(
                pagerState = pagerState,
                imageUrls = state.imageUrls
            )

            if (pagerState.currentPage == 0) {
                SkipButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp),
                    onClick = {
                        onNavigateToHome()
                    }
                )
            }

            OnboardingIndicator(
                currentPage = pagerState.currentPage,
                totalPages = pagerState.pageCount,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 140.dp)
            )

            if (pagerState.currentPage == pagerState.pageCount - 1) {
                GetStartedButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 32.dp),
                    onClick = {
                        onNavigateToHome()
                    }
                )
            }
        }
    }
}
