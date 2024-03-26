package com.tmdb.app.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val TMdbHomeRoute = "movies/home"

internal fun NavGraphBuilder.tmdbHomeScreens() = composable(
    route = TMdbHomeRoute
) {
    TMdbHomeScreen()
}
