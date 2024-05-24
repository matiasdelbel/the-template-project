package com.showcase.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.ui.NavHostScaffold
import com.dbel.design.system.ui.TopAppBar
import com.dbel.design.system.ui.primaryTopAppBarColors
import com.rijks.app.ui.ArtCollectionRoute
import com.rijks.app.ui.rijksScreens
import com.showcase.app.selector.AppSelectorRoute
import com.showcase.app.selector.HolidaysBudgetScreens
import com.showcase.app.selector.RijksScreens
import com.showcase.app.selector.TmdbScreens
import com.showcase.app.selector.TrackingScreens
import com.showcase.app.selector.appSelectorScreen
import com.showcase.app.selector.holidayBudgetScreens
import com.showcase.app.selector.triominosScreens
import com.tmdb.app.ui.popular.PopularMovieCollectionRoute
import com.tmdb.app.ui.tmdbScreens
import com.tracking.app.ui.flows.WeekListSummaryScreenRoute
import com.tracking.app.ui.trackingScreens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppTheme { AppContent() } }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppContent(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
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
        appSelectorScreen(navController)
        holidayBudgetScreens(navController)
        rijksScreens(navController)
        tmdbScreens(navController)
        trackingScreens(navController)
        triominosScreens(navController)
    }
}

val routesWithPrimaryTopAppBarColor = HolidaysBudgetScreens.topRoutes + RijksScreens.topRoutes + TmdbScreens.topRoutes + TrackingScreens.topRoutes
val routesWithoutNavigationIcon = listOf(AppSelectorRoute) + HolidaysBudgetScreens.topRoutes + RijksScreens.topRoutes + TmdbScreens.topRoutes  + TrackingScreens.topRoutes

@Preview(showBackground = true)
@Composable
fun AppPreview() = AppTheme { AppContent() }
