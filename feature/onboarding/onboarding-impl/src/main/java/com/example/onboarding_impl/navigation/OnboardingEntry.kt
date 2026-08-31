package com.example.onboarding_impl.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.onboarding_api.OnboardingRoute
import com.example.onboarding_impl.presentation.OnboardingScreen
import com.example.onboarding_impl.presentation.OnboardingViewModel
import org.koin.androidx.compose.koinViewModel

fun EntryProviderScope<NavKey>.onboardingEntry(
    onNavigateToHome: () -> Unit
) {
    entry<OnboardingRoute> {
        val viewModel: OnboardingViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        OnboardingScreen(
            state = state,
            onNavigateToHome = onNavigateToHome
        )
    }
}