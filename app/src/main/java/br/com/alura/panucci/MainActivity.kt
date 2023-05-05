package br.com.alura.panucci

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.alura.panucci.navigation.*
import br.com.alura.panucci.navigation.highLightsRoute
import br.com.alura.panucci.ui.components.BottomAppBarItem
import br.com.alura.panucci.ui.components.PanucciBottomAppBar
import br.com.alura.panucci.ui.components.bottomAppBarItems
import br.com.alura.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination

            LaunchedEffect(Unit) {
                navController.addOnDestinationChangedListener { _, _, _ ->
                    val routes = navController.backQueue.map {
                        it.destination.route
                    }
                    Log.i("MainActivity", "onCreate: back stack - $routes")
                }
            }

            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentRoute = currentDestination?.route
                    val selectedItem by remember(currentDestination) {
                        val item = when (currentRoute) {
                            highLightsRoute -> BottomAppBarItem.Highlights
                            menuRoute -> BottomAppBarItem.Menu
                            drinksRoute -> BottomAppBarItem.Drinks
                            else -> BottomAppBarItem.Highlights
                        }
                        mutableStateOf(item)
                    }
                    val containsInBottomAppBarItems = when (currentRoute) {
                        highLightsRoute, menuRoute, drinksRoute -> true
                        else -> false
                    }

                    val isShowFab = when (currentDestination?.route) {
                        menuRoute, drinksRoute -> true
                        else -> false
                    }

                    PanucciApp(
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = { item ->
                            val (route, navigate) = when (item) {
                                BottomAppBarItem.Drinks -> Pair(
                                    drinksRoute,
                                    navController::navigateToDrinks
                                )
                                BottomAppBarItem.Highlights -> Pair(
                                    highLightsRoute,
                                    navController::navigateToHighLights
                                )
                                BottomAppBarItem.Menu -> Pair(
                                    menuRoute,
                                    navController::navigateToMenu
                                )
                            }
                            val navOptions = navOptions {
                                launchSingleTop = true
                                popUpTo(route)
                            }
                            navigate(navOptions)

                        },
                        onFabClick = {
                            navController.navigateToCheckout()
                        },
                        isShowTopBar = containsInBottomAppBarItems,
                        isShowBottomBar = containsInBottomAppBarItems,
                        isShowFab = isShowFab
                    ) {
                        AppNavHost(navController = navController)
                    }
                }
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PanucciApp(
        bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
        onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
        onFabClick: () -> Unit = {},
        isShowTopBar: Boolean = false,
        isShowBottomBar: Boolean = false,
        isShowFab: Boolean = false,
        content: @Composable () -> Unit
    ) {
        Scaffold(
            topBar = {
                if (isShowTopBar) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Restaurant Panucci")
                        },
                    )
                }
            },
            bottomBar = {
                if (isShowBottomBar) {
                    PanucciBottomAppBar(
                        item = bottomAppBarItemSelected,
                        items = bottomAppBarItems,
                        onItemChange = onBottomAppBarItemSelectedChange,
                    )
                }
            },
            floatingActionButton = {
                if (isShowFab) {
                    FloatingActionButton(
                        onClick = onFabClick
                    ) {
                        Icon(
                            Icons.Filled.PointOfSale,
                            contentDescription = null
                        )
                    }
                }
            }
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                content()
            }
        }
    }

    @Preview
    @Composable
    private fun PanucciAppPreview() {
        PanucciTheme {
            Surface {
                PanucciApp {}

            }
        }
    }
}