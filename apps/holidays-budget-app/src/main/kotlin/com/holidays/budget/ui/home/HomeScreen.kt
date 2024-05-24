package com.holidays.budget.ui.home

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
import com.holidays.budget.R
import com.holidays.budget.ui.example.ExampleRoute
import com.holidays.budget.ui.example.exampleScreen

const val HomeRoute = "holidays/budget/home"

fun NavGraphBuilder.holidayHomeScreen() = composable(route = HomeRoute) { HomeScreen() }

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
