package com.triominos.home

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
import com.triominos.R
import com.triominos.example.ExampleRoute
import com.triominos.example.exampleScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = ExampleRoute,
        contentWindowInsets = WindowInsets(left = AppTheme.paddings.small, right = AppTheme.paddings.small),
        builder = { exampleScreen() },
        topBar = { destination -> TopBar(destination) { navController.navigateUp() } },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    currentDestination: NavDestination,
    onNavigateUp: () -> Unit
) = TopAppBar(
    title = when (currentDestination.route) {
        HomeRoute -> stringResource(R.string.home_example)
        else -> ""
    },
    colors = when (currentDestination.route) {
        HomeRoute -> TopAppBarDefaults.primaryTopAppBarColors()
        else -> TopAppBarDefaults.topAppBarColors()
    },
    isNavigationIconVisible = currentDestination.route != HomeRoute,
    onNavigateUp = onNavigateUp
)

private const val HomeRoute = "triominos/home"
