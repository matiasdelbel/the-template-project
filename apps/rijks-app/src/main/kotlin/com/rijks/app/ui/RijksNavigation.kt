package com.rijks.app.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.rijksScreens(navController: NavController) {
    artCollectionScreen(onArtObjectSelected = { artObject -> navController.navigateToArtObject(artObject) })
    artObjectScreen()
}
