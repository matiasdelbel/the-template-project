package com.tmdb.app.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.ui.BottomBar
import com.dbel.design.system.ui.NavHostScaffold
import com.dbel.design.system.ui.Screen
import com.dbel.design.system.ui.TopAppBar
import com.dbel.design.system.ui.primaryTopAppBarColors
import com.tmdb.app.R
import com.tmdb.app.tmDbScreenList
import com.tmdb.app.ui.popular.PopularMovieCollectionRoute
import com.tmdb.app.ui.top.TopRatedMovieCollectionRoute
import com.tmdb.app.ui.upcoming.UpcomingMovieCollectionRoute

@Composable
fun TMdbHomeScreen(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = startRoute,
        contentWindowInsets = WindowInsets(left = AppTheme.paddings.small, right = AppTheme.paddings.small),
        builder = { tmDbScreenList() },
        topBar = { destination -> TopBar(destination) { navController.navigateUp()} },
        bottomBar = { BottomBar(navController, screens = listOf(Populars, Upcoming, TopRated)) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    currentDestination: NavDestination,
    onNavigateUp: () -> Unit
) = TopAppBar(
    title = topAppBarTitle[currentDestination.route]?.let { stringResource(id = it) } ?: "",
    colors = when (currentDestination.route) {
        in routesWithPrimaryTopAppBarColor -> TopAppBarDefaults.primaryTopAppBarColors()
        else -> TopAppBarDefaults.topAppBarColors()
    },
    isNavigationIconVisible = currentDestination.route !in routesWithPrimaryTopAppBarColor,
    onNavigateUp = onNavigateUp
)

val startRoute = PopularMovieCollectionRoute

val routesWithPrimaryTopAppBarColor = listOf(
    TopRatedMovieCollectionRoute,
    UpcomingMovieCollectionRoute,
    PopularMovieCollectionRoute
)

val topAppBarTitle = mapOf(
    TopRatedMovieCollectionRoute to R.string.top_rated,
    UpcomingMovieCollectionRoute to R.string.upcoming,
    PopularMovieCollectionRoute to R.string.populars,
)

val TopRated = Screen(TopRatedMovieCollectionRoute, R.string.top_rated, iconId = R.drawable.ic_thumb_up)
val Upcoming = Screen(UpcomingMovieCollectionRoute, R.string.upcoming, iconId = R.drawable.ic_upcoming)
val Populars = Screen(PopularMovieCollectionRoute, R.string.populars, iconId = R.drawable.ic_trending)
