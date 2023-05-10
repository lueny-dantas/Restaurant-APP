package br.com.alura.panucci

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import br.com.alura.panucci.navigation.AppNavHost
import br.com.alura.panucci.navigation.drinksRoute
import br.com.alura.panucci.navigation.highLightsRoute
import br.com.alura.panucci.navigation.menuRoute
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
    fun appNavhost_verifyIsHighlightsScreenIsDisplayed(){
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule.onNodeWithText("Menu")
            .performClick()

        composeTestRule.onAllNodesWithText("Destaques")
            .assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, highLightsRoute)
    }

    @Test
    fun appNavhost_verifyIfMenuScreenIsDisplayed(){
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule.onNodeWithText("Menu")
            .performClick()

        composeTestRule.onAllNodesWithText("Menu")
            .assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, menuRoute)
    }

    @Test
    fun appNavhost_verifyIfDrinksScreenIsDisplayed(){
        composeTestRule.onRoot().printToLog("panucci app")
        composeTestRule.onNodeWithText("Bebidas")
            .performClick()

        composeTestRule.onAllNodesWithText("Bebidas")
            .assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, drinksRoute)
    }

}