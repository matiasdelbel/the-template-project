package com.tmdb.app.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homePane() = composable(
    route = TMdbHomeRoute,
    content = { TMdbHome() }
)

const val TMdbHomeRoute = "movies/home"
