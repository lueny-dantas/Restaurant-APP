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
import br.com.alura.panucci.navigation.AppDestination
import br.com.alura.panucci.navigation.AppNavHost
import br.com.alura.panucci.navigation.bottomAppBarItems
import br.com.alura.panucci.navigation.navigateToCheckout
import br.com.alura.panucci.ui.components.BottomAppBarItem
import br.com.alura.panucci.ui.components.PanucciBottomAppBar
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
                    var selectedItem by remember(currentDestination) {
                        val item = currentDestination?.let { destination ->
                            bottomAppBarItems.find {
                                it.destination.route == destination.route
                            }
                        } ?: bottomAppBarItems.first()
                        mutableStateOf(item)
                    }
                    val containsInBottomAppBarItems = currentDestination?.let { destination ->
                        bottomAppBarItems.find {
                            it.destination.route == destination.route
                        }
                    } != null
                    val isShowFab = when (currentDestination?.route) {
                        AppDestination.Menu.route,
                        AppDestination.Drinks.route -> true
                        else -> false
                    }
                    PanucciApp(
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = {
                            val route = it.destination.route
                            navController.navigate(route) {
                                launchSingleTop = true
                                popUpTo(route)
                            }
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