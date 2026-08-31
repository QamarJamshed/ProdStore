package com.example.productdetails_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.ProductRepository
import com.example.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productId: Int,
    private val productRepository: ProductRepository,
    private val wishlistRepository: WishlistRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailsState())
    val state: StateFlow<ProductDetailsState> = _state.asStateFlow()

    init {
        loadProduct()
    }

    fun onIntent(intent: ProductDetailsIntent) {
        when (intent) {
            is ProductDetailsIntent.ToggleWishlist -> toggleWishlist(intent.product)
            ProductDetailsIntent.Retry -> loadProduct()
        }
    }

    private fun loadProduct() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            productRepository.getProductById(productId)
                .onSuccess { product ->
                    _state.update { it.copy(isLoading = false, product = product) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    private fun toggleWishlist(product: com.example.domain.model.Product) {
        viewModelScope.launch {
            wishlistRepository.toggleWishlist(product)
            // Refresh product to get updated wishlist status
            val isWishlisted = wishlistRepository.isWishlisted(product.id)
            _state.update { currentState ->
                currentState.copy(
                    product = currentState.product?.copy(isWishlisted = isWishlisted)
                )
            }
        }
    }
}
