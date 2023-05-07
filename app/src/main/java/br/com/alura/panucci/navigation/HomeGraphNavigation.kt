package br.com.alura.panucci.navigation

import androidx.navigation.*
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.ui.components.BottomAppBarItem

internal const val homeGraphRoute = "home"

fun NavGraphBuilder.homeGraph(
    onNavigateToCheckout: () -> Unit,
    onNavigateToProductDetails: (Product) -> Unit
) {
    navigation(
        startDestination = highLightsRoute,
        route = homeGraphRoute
    ) {
        highLightsScreen(onNavigateToCheckout,onNavigateToProductDetails)
        menuScreen(onNavigateToProductDetails)
        drinksScreen(onNavigateToProductDetails)
    }
}

fun NavController.navigateToHomeGraph() {
    navigate(homeGraphRoute)
}

fun NavController.navigateItemWithPopUpTo(
    item: BottomAppBarItem
) {
    val (route, navigate) = when (item) {
        BottomAppBarItem.Drinks -> Pair(
            drinksRoute,
            ::navigateToDrinks
        )
        BottomAppBarItem.Highlights -> Pair(
            highLightsRoute,
            ::navigateToHighLights
        )
        BottomAppBarItem.Menu -> Pair(
            menuRoute,
            ::navigateToMenu
        )
    }
    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(route)
    }
    navigate(navOptions)
}
