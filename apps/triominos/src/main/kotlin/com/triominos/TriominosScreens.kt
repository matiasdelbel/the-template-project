package com.triominos

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.app.container.contract.AppScreens
import com.triominos.example.exampleScreen
import com.triominos.home.HomeRoute
import com.triominos.home.HomeScreen
import com.triominos.home.homeScreen

object TriominosScreens : AppScreens {

    override val startRoute: String = HomeRoute

    override val topRoutes: List<String> = listOf(
        HomeRoute,
    )

    @Composable
    override fun Home() = HomeScreen()
}

fun NavGraphBuilder.triominosScreens(navController: NavController) {
    homeScreen()
    exampleScreen()
}
