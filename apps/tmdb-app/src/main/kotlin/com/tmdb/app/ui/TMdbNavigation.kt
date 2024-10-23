package com.tmdb.app.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tmdb.app.ui.home.homePane
import com.tmdb.app.ui.popular.popularMoviesScreens
import com.tmdb.app.ui.top.topRatedMoviesScreens
import com.tmdb.app.ui.upcoming.upcomingMoviesScreens

fun NavGraphBuilder.tmdbScreens(navController: NavController) {
    homePane()
    popularMoviesScreens()
    topRatedMoviesScreens()
    upcomingMoviesScreens()
}