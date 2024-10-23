package com.tmdb.app.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.component.NavHostScaffold
import com.dbel.design.system.component.TopAppBar
import com.dbel.design.system.component.primaryTopAppBarColors
import com.tmdb.app.R
import com.tmdb.app.ui.listing.MoviesRoute
import com.tmdb.app.ui.listing.moviesPane

@Composable
fun TMdbHome(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = MoviesRoute,
        contentWindowInsets = WindowInsets(
            left = AppTheme.paddings.sm,
            right = AppTheme.paddings.sm
        ),
        builder = { moviesPane() },
        topBar = { TopBar { navController.navigateUp()} },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onNavigateUp: () -> Unit) = TopAppBar(
    title = stringResource(id = R.string.tmdb_app_name),
    colors = TopAppBarDefaults.primaryTopAppBarColors(),
    isNavigationIconVisible = false,
    onNavigateUp = onNavigateUp
)
