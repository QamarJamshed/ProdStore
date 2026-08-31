package com.example.onboarding_impl.di

import com.example.onboarding_impl.presentation.OnboardingViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val onboardingModule = module {
   viewModel {
       OnboardingViewModel(get())
   }
}