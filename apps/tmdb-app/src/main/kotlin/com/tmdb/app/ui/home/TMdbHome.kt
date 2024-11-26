package com.tmdb.app.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.component.NavHostScaffold
import com.tmdb.app.ui.listing.MoviesRoute
import com.tmdb.app.ui.listing.moviesPane

@Composable
fun TMdbHome(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = MoviesRoute,
        builder = { moviesPane() },
        modifier = modifier
    )
}
