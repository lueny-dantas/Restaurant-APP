package br.com.alura.panucci.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import br.com.alura.panucci.ui.screens.ProductDetailsScreen
import br.com.alura.panucci.ui.viewmodels.ProductDetailsViewModel

const val productDetailsRoute = "productDetails"
internal const val productIdArgument = "productId"

fun NavGraphBuilder.productDetailsScreen(
    onNavigateToCheckout: () -> Unit,
    onPopBackStack: () -> Unit
) {
    composable(
        "$productDetailsRoute/{$productIdArgument}",
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "$uri/$productDetailsRoute/{$productIdArgument}"
            }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(productIdArgument)?.let { id ->
            val viewModel = viewModel<ProductDetailsViewModel>(
                factory = ProductDetailsViewModel.Factory
            )
            val uiState by viewModel.uiState.collectAsState()

            ProductDetailsScreen(
                uiState = uiState,
                onTryFindProductAgainClick = {
                    viewModel.findProductById(id)
                },
                onOrderClick = onNavigateToCheckout,
                onBackClick = onPopBackStack,
            )
        } ?: LaunchedEffect(Unit) {
            onPopBackStack()
        }
    }
}

fun NavController.navigateToProductDetails(id: String) {
    navigate("$productDetailsRoute/$id")
}