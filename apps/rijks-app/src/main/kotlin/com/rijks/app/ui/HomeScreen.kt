package com.rijks.app.ui

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
import com.dbel.design.system.component.NavHostScaffold
import com.dbel.design.system.component.TopAppBar
import com.dbel.design.system.component.primaryTopAppBarColors
import com.tmdb.app.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = ArtCollectionRoute,
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
    title = if (currentDestination.route == ArtCollectionRoute) stringResource(R.string.rijks_app_name) else "",
    colors = when (currentDestination.route) {
        ArtCollectionRoute -> TopAppBarDefaults.primaryTopAppBarColors()
        else -> TopAppBarDefaults.topAppBarColors()
    },
    isNavigationIconVisible = currentDestination.route != ArtCollectionRoute,
    onNavigateUp = onNavigateUp
)
