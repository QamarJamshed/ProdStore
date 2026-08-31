package com.example.ecommerceapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.home_api.HomeRoute
import com.example.home_impl.navigation.homeEntry
import com.example.onboarding_api.OnboardingRoute
import com.example.onboarding_impl.navigation.onboardingEntry
import com.example.productdetails_api.ProductDetailsRoute
import com.example.productdetails_impl.navigation.productDetailsEntry
import com.example.wishlist_api.WishlistRoute
import com.example.wishlist_impl.navigation.wishlistEntry

@Composable
fun EcommerceApp() {
    val backStack = rememberNavBackStack(OnboardingRoute)
    
    // Explicitly resolve the host ViewModelStoreOwner. 
    // Fallback to the context if LocalViewModelStoreOwner.current is null (rare in standard apps)
//    val hostOwner = LocalViewModelStoreOwner.current
//        ?: (LocalContext.current as? ViewModelStoreOwner)
//        ?: error("No ViewModelStoreOwner found in composition or context")

    val hostOwner = LocalViewModelStoreOwner.current
        ?: error("No ViewModelStoreOwner found")
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(viewModelStoreOwner = hostOwner)
        ),
        entryProvider = entryProvider {
            onboardingEntry(
                onNavigateToHome = {
                    backStack.clear()
                    backStack.add(HomeRoute)
                },
            )
            homeEntry(
                onNavigateToProductDetails = { productId ->
                    backStack.add(ProductDetailsRoute(productId))
                },
                onNavigateToWishlist = {
                    backStack.add(WishlistRoute)
                }
            )
            productDetailsEntry(
                onBack = {
                    backStack.removeLastOrNull()
                }
            )
            wishlistEntry(
                onProductClick = { productId ->
                    backStack.add(ProductDetailsRoute(productId))
                },
                onNavigateToHome = {
                    backStack.clear()
                    backStack.add(HomeRoute)
                }
            )
        }
    )
}
