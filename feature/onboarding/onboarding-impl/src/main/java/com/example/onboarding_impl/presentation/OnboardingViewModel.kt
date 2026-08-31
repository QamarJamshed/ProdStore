package com.example.onboarding_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    init {
        fetchOnboardingImages()
    }

    private fun fetchOnboardingImages() {
        viewModelScope.launch {
            productRepository.getProducts(limit = 2).onSuccess { products ->
                _state.update { it.copy(imageUrls = products.map { product -> product.thumbnail }) }
            }
        }
    }
}
