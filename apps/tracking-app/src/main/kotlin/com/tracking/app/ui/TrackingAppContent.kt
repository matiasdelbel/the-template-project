package com.tracking.app.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.component.NavHostScaffold
import com.dbel.design.system.component.TopAppBar
import com.tracking.app.R
import com.tracking.app.ui.flows.WeekListSummaryScreenRoute
import com.tracking.app.ui.profile.ProfileScreenRoute
import com.tracking.app.ui.workout.HomeScreenRoute

@Composable
fun TrackingAppContent(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = HomeScreenRoute,
        contentWindowInsets = WindowInsets(
            left = AppTheme.spacers.md,
            right = AppTheme.spacers.md,
            top = AppTheme.spacers.md,
            bottom = AppTheme.spacers.md,
        ),
        builder = { trackingScreens(navController) },
        topBar = { destination -> TopBar(destination) { navController.navigateUp()} },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    currentDestination: NavDestination,
    onNavigateUp: () -> Unit
) = TopAppBar(
    title = topAppBarTitle[currentDestination.route]?.let { stringResource(id = it) } ?: "",
    isNavigationIconVisible = currentDestination.route !in routesWithPrimaryTopAppBarColor,
    onNavigateUp = onNavigateUp
)

val routesWithPrimaryTopAppBarColor = listOf(
    HomeScreenRoute,
    WeekListSummaryScreenRoute,
    ProfileScreenRoute,
)

val topAppBarTitle = mapOf(
    WeekListSummaryScreenRoute to R.string.home_tracking,
    HomeScreenRoute to R.string.workouts,
    ProfileScreenRoute to R.string.home_profile,
)
