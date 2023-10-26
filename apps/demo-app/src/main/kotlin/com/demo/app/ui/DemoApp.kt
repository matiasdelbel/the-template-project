package com.demo.app.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.ui.NavHostScaffold
import com.dbel.design.system.ui.TopAppBar
import com.dbel.design.system.ui.primaryTopAppBarColors
import com.demo.app.demoAppScreenList
import com.demo.app.R
import com.demo.app.ui.selector.AppSelectorRoute
import com.rijks.app.rijksScreenList
import com.rijks.app.ui.ArtCollectionRoute
import com.tmdb.app.tmDbScreenList
import com.tmdb.app.ui.PopularMovieCollectionRoute

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DemoApp(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = AppSelectorRoute,
        topBar = { currentDestination ->
            TopAppBar(
                title = when (currentDestination.route) {
                    AppSelectorRoute -> stringResource(R.string.app_name)
                    ArtCollectionRoute -> stringResource(R.string.rijks_app_name)
                    PopularMovieCollectionRoute -> stringResource(R.string.tmdb_app_name)
                    else -> ""
                },
                colors = when (currentDestination.route) {
                    in routesWithPrimaryTopAppBar -> TopAppBarDefaults.primaryTopAppBarColors()
                    else -> TopAppBarDefaults.topAppBarColors()
                },
                isNavigationIconVisible = currentDestination.route !in routesWithPrimaryTopAppBar,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    ) {
        demoAppScreenList(navController)
        rijksScreenList(navController)
        tmDbScreenList()
    }
}

val routesWithPrimaryTopAppBar = listOf(AppSelectorRoute, ArtCollectionRoute, PopularMovieCollectionRoute)
