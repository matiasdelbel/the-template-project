package com.rijks.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.rijks.app.ui.artCollectionScreen
import com.rijks.app.ui.artObjectScreen
import com.rijks.app.ui.navigateToArtObject

fun NavGraphBuilder.rijksScreenList(navController: NavController) {
    artCollectionScreen(onArtObjectSelected = { artObject -> navController.navigateToArtObject(artObject) })
    artObjectScreen()
}