package com.rijks.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.app.container.contract.AppScreens

object RijksScreens : AppScreens {

    override val startRoute = ArtCollectionRoute

    override val topRoutes = listOf(
        ArtCollectionRoute,
    )

    @Composable
    override fun Home() = HomeScreen()
}

fun NavGraphBuilder.rijksScreens(navController: NavController) {
    artCollectionScreen(onArtObjectSelected = { artObject -> navController.navigateToArtObject(artObject) })
    artObjectScreen()
}