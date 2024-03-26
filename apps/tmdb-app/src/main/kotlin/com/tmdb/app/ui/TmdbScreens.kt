package com.tmdb.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.app.container.contract.AppScreens
import com.tmdb.app.ui.home.TMdbHomeRoute
import com.tmdb.app.ui.home.TMdbHomeScreen
import com.tmdb.app.ui.home.tmdbHomeScreens
import com.tmdb.app.ui.popular.PopularMovieCollectionRoute
import com.tmdb.app.ui.popular.popularMoviesScreens
import com.tmdb.app.ui.top.topRatedMoviesScreens
import com.tmdb.app.ui.upcoming.upcomingMoviesScreens

object TmdbScreens : AppScreens {

    override val startRoute: String = TMdbHomeRoute

    override val topRoutes: List<String> = listOf(
        TMdbHomeRoute,
        PopularMovieCollectionRoute,
    )

    @Composable
    override fun Home() = TMdbHomeScreen()
}

fun NavGraphBuilder.tmdbScreens(navController: NavController) {
    tmdbHomeScreens()
    popularMoviesScreens()
    topRatedMoviesScreens()
    upcomingMoviesScreens()
}
