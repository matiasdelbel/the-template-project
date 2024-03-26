package com.tracking.app.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
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
import com.tracking.app.R
import com.tracking.app.ui.flows.WeekListSummaryScreenRoute
import com.tracking.app.ui.profile.ProfileScreenRoute
import com.tracking.app.ui.trackingScreens

@Composable
fun TrackingHomeScreen(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = startRoute,
        contentWindowInsets = WindowInsets(left = AppTheme.paddings.small, right = AppTheme.paddings.small),
        builder = { trackingScreens(navController) },
        topBar = { destination -> TopBar(destination) { navController.navigateUp()} },
        bottomBar = { BottomBar(navController, screens = listOf(Tracking, Profile)) },
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

val startRoute = WeekListSummaryScreenRoute

val routesWithPrimaryTopAppBarColor = listOf(
    WeekListSummaryScreenRoute,
    ProfileScreenRoute,
)

val topAppBarTitle = mapOf(
    WeekListSummaryScreenRoute to R.string.home_tracking,
    ProfileScreenRoute to R.string.home_profile,
)

val Tracking = Screen(WeekListSummaryScreenRoute, R.string.home_tracking, iconId = R.drawable.ic_trending_up)
val Profile = Screen(ProfileScreenRoute, R.string.home_profile, iconId = R.drawable.ic_person)
