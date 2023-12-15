package com.showcase.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.ui.NavHostScaffold
import com.dbel.design.system.ui.TopAppBar
import com.dbel.design.system.ui.primaryTopAppBarColors
import com.rijks.app.rijksScreenList
import com.rijks.app.ui.ArtCollectionRoute
import com.tmdb.app.tmDbScreenList
import com.tracking.app.trackingAppScreenList
import com.tracking.app.ui.flows.WeekListSummaryScreenRoute
import com.showcase.app.selector.AppSelectorRoute
import com.showcase.app.selector.appSelectorScreen
import com.tmdb.app.ui.home.TMdbHomeRoute
import com.tmdb.app.ui.popular.PopularMovieCollectionRoute

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ShowcaseApp(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = AppSelectorRoute,
        topBar = { currentDestination ->
            TopAppBar(
                title =
                when (currentDestination.route) {
                    AppSelectorRoute -> stringResource(R.string.showcase_app_name)
                    ArtCollectionRoute -> stringResource(R.string.rijks_app_name)
                    PopularMovieCollectionRoute -> stringResource(R.string.tmdb_app_name)
                    WeekListSummaryScreenRoute -> stringResource(R.string.tracking_app_name)
                    else -> ""
                },
                colors = when (currentDestination.route) {
                    in routesWithPrimaryTopAppBarColor -> TopAppBarDefaults.primaryTopAppBarColors()
                    AppSelectorRoute -> TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    else -> TopAppBarDefaults.topAppBarColors()
                },
                isNavigationIconVisible = currentDestination.route !in routesWithoutNavigationIcon,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    ) {
        appSelectorScreen(
            onRijksAppSelected = { navController.navigate(ArtCollectionRoute) },
            onTmDbAppSelected = { navController.navigate(TMdbHomeRoute) },
            onTrackingAppSelected = { navController.navigate(WeekListSummaryScreenRoute) }
        )

        rijksScreenList(navController)
        tmDbScreenList()
        trackingAppScreenList(navController)
    }
}

val routesWithPrimaryTopAppBarColor = listOf(
    ArtCollectionRoute,
    TMdbHomeRoute,
    PopularMovieCollectionRoute,
    WeekListSummaryScreenRoute,
)

val routesWithoutNavigationIcon = listOf(
    AppSelectorRoute,
    ArtCollectionRoute,
    TMdbHomeRoute,
    PopularMovieCollectionRoute,
    WeekListSummaryScreenRoute,
)
