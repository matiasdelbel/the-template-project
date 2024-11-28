package com.rijks.app.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.component.NavHostScaffold

@Composable
fun HomeScreen(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = ArtCollectionRoute,
        builder = {
            artCollectionPane(
                onArtObjectSelected = { artObject -> navController.navigateToArtObject(artObject) }
            )
            artObjectPane()
        },
    )
}
