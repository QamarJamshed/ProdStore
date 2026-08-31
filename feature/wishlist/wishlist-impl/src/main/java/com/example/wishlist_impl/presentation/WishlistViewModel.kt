package com.example.wishlist_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val wishlistRepository: WishlistRepository
) : ViewModel() {

    private val _state = MutableStateFlow(WishlistState())
    val state: StateFlow<WishlistState> = _state.asStateFlow()

    init {
        observeWishlist()
    }

    fun onIntent(intent: WishlistIntent) {
        when (intent) {
            is WishlistIntent.RemoveFromWishlist -> removeFromWishlist(intent.product)
        }
    }

    private fun observeWishlist() {
        wishlistRepository.getWishlist()
            .onEach { products ->
                _state.update { it.copy(products = products, isLoading = false) }
            }
            .catch { error ->
                _state.update { it.copy(error = error.message, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    private fun removeFromWishlist(product: Product) {
        viewModelScope.launch {
            wishlistRepository.toggleWishlist(product)
        }
    }
}
