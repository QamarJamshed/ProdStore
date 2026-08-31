package com.example.onboarding_impl.presentation

data class OnboardingState(
    val totalPage: Int = 2,
    val imageUrls: List<String> = emptyList()
)