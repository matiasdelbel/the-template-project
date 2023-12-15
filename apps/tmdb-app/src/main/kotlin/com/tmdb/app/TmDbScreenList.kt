package com.tmdb.app

import androidx.navigation.NavGraphBuilder
import com.tmdb.app.ui.home.tmdbHomeScreens
import com.tmdb.app.ui.popular.popularMoviesScreens
import com.tmdb.app.ui.top.topRatedMoviesScreens
import com.tmdb.app.ui.upcoming.upcomingMoviesScreens

fun NavGraphBuilder.tmDbScreenList() {
    tmdbHomeScreens()
    popularMoviesScreens()
    topRatedMoviesScreens()
    upcomingMoviesScreens()
}
