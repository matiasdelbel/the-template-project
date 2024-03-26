package com.rijks.app.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.ui.NavHostScaffold
import com.dbel.design.system.ui.TopAppBar
import com.dbel.design.system.ui.primaryTopAppBarColors
import com.tmdb.app.R

@Composable
internal fun HomeScreen(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = RijksScreens.startRoute,
        contentWindowInsets = WindowInsets(left = AppTheme.paddings.small, right = AppTheme.paddings.small),
        builder = { rijksScreens(navController)  },
        topBar = { destination -> TopBar(destination) { navController.navigateUp()} },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    currentDestination: NavDestination,
    onNavigateUp: () -> Unit
) = TopAppBar(
    title = if (currentDestination.route == RijksScreens.startRoute) stringResource(R.string.rijks_app_name) else "",
    colors = when (currentDestination.route) {
        in RijksScreens.topRoutes -> TopAppBarDefaults.primaryTopAppBarColors()
        else -> TopAppBarDefaults.topAppBarColors()
    },
    isNavigationIconVisible = currentDestination.route !in RijksScreens.topRoutes,
    onNavigateUp = onNavigateUp
)
