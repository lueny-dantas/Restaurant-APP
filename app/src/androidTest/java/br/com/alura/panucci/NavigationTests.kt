package br.com.alura.panucci

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import br.com.alura.panucci.navigation.*
import br.com.alura.panucci.sampledata.sampleProducts
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTests {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            PanucciApp(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule
            .onNodeWithText("Destaques do dia")
            .assertIsDisplayed()
    }

    @Test
    fun appNavHost_verifyIsHighlightsScreenIsDisplayed() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onNodeWithText("Destaques")
            .performClick()
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithText("Destaques")


        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, highLightsRoute)
    }

    @Test
    fun appNavHost_verifyIfMenuScreenIsDisplayed() {
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule.onNodeWithText("Menu")
            .performClick()

        composeTestRule.onAllNodesWithText("Menu")
            .assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, menuRoute)
    }

    @Test
    fun appNavHost_verifyIfDrinksScreenIsDisplayed() {
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule.onNodeWithText("Bebidas")
            .performClick()

        composeTestRule.onAllNodesWithText("Bebidas")
            .assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, drinksRoute)
    }

    @Test
    fun appNavHost_verifyIfProductDetailsScreenIsDisplayedFromHighlightsListScreen() {
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule
            .onAllNodesWithContentDescription("highlight product card item")
            .onFirst()
            .performClick()

        composeTestRule.waitUntil(3000) {
            composeTestRule.onAllNodesWithText("Falha ao buscar produto")
                .fetchSemanticsNodes().size == 1
        }

        composeTestRule.onNodeWithText("Falha ao buscar produto")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "$productDetailsRoute/{$productIdArgument}")

    }

    @Test
    fun appNavHost_verifyIfProductDetailsScreenIsDisplayedFromMenuScreen() {
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule.onNodeWithText("Menu")
            .performClick()

        composeTestRule
            .onAllNodesWithContentDescription("menu product card item")
            .onFirst()
            .performClick()

        composeTestRule.waitUntil (3000){
            composeTestRule.onAllNodesWithContentDescription("product details content")
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeWithContentDescription("product details content")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "$productDetailsRoute/{$productIdArgument}")
    }

    @Test
    fun appNavHost_verifyIfProductDetailsScreenIsDisplayedFromDrinksScreen() {
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule.onNodeWithText("Bebidas")
            .performClick()

        composeTestRule
            .onAllNodesWithContentDescription("drinks product card item")
            .onFirst()
            .performClick()

        composeTestRule.waitUntil (3000){
            composeTestRule.onAllNodesWithContentDescription("product details content")
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeWithContentDescription("product details content")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "$productDetailsRoute/{$productIdArgument}")
    }

    @Test
    fun appNavHost_VerifyIfCheckoutScreenIsDisplayedFromHighlightsListScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onAllNodesWithText("Pedir")
            .onFirst()
            .performClick()

        composeTestRule.onNodeWithText("Pedido")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, checkoutRoute)
    }

    @Test
    fun appNavHost_VerifyIfCheckoutScreenIsDisplayedFromMenuScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onNodeWithText("Menu")
            .performClick()

        composeTestRule.onNodeWithContentDescription("Floating Action Button for order")
            .performClick()

        composeTestRule.onNodeWithText("Pedido")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, checkoutRoute)
    }

    @Test
    fun appNavHost_VerifyIfCheckoutScreenIsDisplayedFromDrinksScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onNodeWithText("Bebidas")
            .performClick()

        composeTestRule.onNodeWithContentDescription("Floating Action Button for order")
            .performClick()

        composeTestRule.onNodeWithText("Pedido")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, checkoutRoute)
    }

    @Test
    fun appNavHost_VerifyIfCheckoutScreenIsDisplayedFromProductDetailsScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.runOnUiThread {
            navController.navigateToProductDetails(sampleProducts.first().id)
        }

        composeTestRule.waitUntil(3000) {
            composeTestRule.onAllNodesWithText("Pedir")
                .fetchSemanticsNodes().size == 1
        }

        composeTestRule.onNodeWithText("Pedir")
            .performClick()

        composeTestRule.onNodeWithText("Pedido")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, checkoutRoute)
    }

    @Test
    fun appNavHost_verifyIfSnackbarIsDisplayedWhenFinishTheOrder() {
        composeTestRule.runOnUiThread {
            navController.navigateToCheckout()
        }

        composeTestRule.onNodeWithText("Pedir")
            .performClick()

        // Com o semantics podemos utilizar tags para identificar um componente durante o test.
        composeTestRule.onNodeWithTag("PanucciSnackbar")
            .assertIsDisplayed()
    }

    /*
    * Esse teste faz as verificações necessárias e como podemos ver é grande!
    * Nos próximos começo a aplicar algumas técnicas para simplificar e reutilizar */
    @Test
    fun appNavHost_verifyIfBottomAppBarIsDisplayedOnlyInHomeGraphNavigation() {
        composeTestRule.onRoot().printToLog("panucci app")

        val bottomAppBarTag = "PanucciBottomAppBar"

        composeTestRule.runOnUiThread {
            navController.navigateToHighLights()
        }
        composeTestRule.onNodeWithTag(bottomAppBarTag)
            .assertIsDisplayed()

        composeTestRule.runOnUiThread {
            navController.navigateToMenu()
        }
        composeTestRule.onNodeWithTag(bottomAppBarTag)
            .assertIsDisplayed()

        composeTestRule.runOnUiThread {
            navController.navigateToDrinks()
        }
        composeTestRule.onNodeWithTag(bottomAppBarTag)
            .assertIsDisplayed()

        composeTestRule.runOnUiThread {
            navController.navigateToProductDetails(sampleProducts.random().id)
        }
        // Foi usado o `assertDoesNotExist()`, pois o `assertIsNotDisplayed()` só funciona se achar o nó
        composeTestRule.onNodeWithTag(bottomAppBarTag)
            .assertDoesNotExist()

        composeTestRule.runOnUiThread {
            navController.navigateToCheckout()
        }
        composeTestRule.onNodeWithTag(bottomAppBarTag)
            .assertDoesNotExist()
    }


    /* Essa versão do teste já extrai a busca dos nós a partir de funções.
    * Dessa forma, podemos fazer a busca novamente do elemento esperado apenas executando a variável */
    @Test
    fun appNavHost_verifyIfFabIsDisplayedOnlyInMenuOrDrinksDestination() {
        composeTestRule.onRoot().printToLog("panucci app")

        val fabContentDescription = "Floating Action Button for order"

        val assertThatFabIsDisplayed = fun ComposeTestRule.() {
            onNodeWithContentDescription(fabContentDescription)
                .assertIsDisplayed()
        }
        composeTestRule.runOnUiThread {
            navController.navigateToMenu()
        }
        composeTestRule.assertThatFabIsDisplayed()

        composeTestRule.runOnUiThread {
            navController.navigateToDrinks()
        }
        composeTestRule.assertThatFabIsDisplayed()

        val assertThatFabDoesNotExist = fun ComposeTestRule.() {
            onNodeWithContentDescription(fabContentDescription)
                .assertDoesNotExist()
        }
        composeTestRule.runOnUiThread {
            navController.navigateToHighLights()
        }
        composeTestRule.assertThatFabDoesNotExist()

        composeTestRule.runOnUiThread {
            navController.navigateToProductDetails(sampleProducts.random().id)
        }
        composeTestRule.assertThatFabDoesNotExist()

        composeTestRule.runOnUiThread {
            navController.navigateToCheckout()
        }
        composeTestRule.assertThatFabDoesNotExist()
    }

    /* Nessa versão já aplico as técnicas para simplificar o código mesmo sendo grande */
    @Test
    fun appNavHost_verifyIfTopAppBarIsDisplayedOnlyInHomeGraphNavigation() =
        with(composeTestRule) {
            onRoot().printToLog("panucci app")
            val topAppBarTag = "PanucciTopAppBar"
            val assertThatTopAppBarIsDisplayed = fun ComposeTestRule.() {
                onNodeWithTag(topAppBarTag)
                    .assertIsDisplayed()
            }
            runOnUiThread {
                navController.navigateToHighLights()
            }
            assertThatTopAppBarIsDisplayed()

            runOnUiThread {
                navController.navigateToMenu()
            }
            assertThatTopAppBarIsDisplayed()

            runOnUiThread {
                navController.navigateToDrinks()
            }
            assertThatTopAppBarIsDisplayed()

            val assertThatTopAppBarDoesNotExist = fun ComposeTestRule.() {
                onNodeWithTag(topAppBarTag)
                    .assertDoesNotExist()
            }
            runOnUiThread {
                navController.navigateToProductDetails(sampleProducts.random().id)
            }
            assertThatTopAppBarDoesNotExist()

            runOnUiThread {
                navController.navigateToCheckout()
            }
            assertThatTopAppBarDoesNotExist()
        }

}