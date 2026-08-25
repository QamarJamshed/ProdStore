package com.example.ecommerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute


@Composable
fun EcommerceNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Onboarding
    ) {
        composable<Onboarding> {

        }

        composable<Home> {

        }

        composable<Wishlist> {

        }

        composable<ProductDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<ProductDetail>()

        }

    }
}
