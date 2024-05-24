package com.tmdb.app.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val TMdbHomeRoute = "movies/home"

fun NavGraphBuilder.tmdbHomeScreens() = composable(
    route = TMdbHomeRoute
) {
    TMdbHomeScreen()
}
