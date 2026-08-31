package com.example.home_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.repository.ProductRepository
import com.example.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val productRepository: ProductRepository,
    private val wishlistRepository: WishlistRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        fetchCategories()
        updateProducts(null)
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.SelectCategory -> {
                if (_state.value.selectedCategory != intent.category) {
                    _state.update { it.copy(selectedCategory = intent.category) }
                    updateProducts(intent.category)
                }
            }
            is HomeIntent.OnProductClick -> {
            }
            is HomeIntent.ToggleWishlist -> {
                toggleWishlist(intent.product)
            }
        }
    }

    private fun toggleWishlist(product: com.example.domain.model.Product) {
        viewModelScope.launch {
            wishlistRepository.toggleWishlist(product)
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            productRepository.getCategories().onSuccess { categories ->
                _state.update { it.copy(categories = categories, isLoading = false) }
            }.onFailure { error ->
                _state.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }

    private fun updateProducts(category: String?) {
        val productsFlow = productRepository.getProductsPaged(category)
            .cachedIn(viewModelScope)
        _state.update { it.copy(products = productsFlow) }
    }
}
