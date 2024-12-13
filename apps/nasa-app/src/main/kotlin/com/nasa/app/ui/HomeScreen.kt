package com.nasa.app.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.component.NavHostScaffold

@Composable
fun NasaHome(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = ImageCollectionRoute,
        builder = {
            imageCollectionPane(onSelected = { /* TODO */  })
        },
    )
}
