package com.tmdb.app.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.tmdbHomeScreens() = composable(
    route = TMdbHomeRoute
) {
    TMdbHomeScreen()
}

private const val TMdbHomeRoute = "movies/home"