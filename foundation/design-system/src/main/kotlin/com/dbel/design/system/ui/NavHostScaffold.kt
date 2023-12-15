package com.dbel.design.system.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavHostScaffold(
    navController: NavHostController,
    startRoute: String,
    modifier: Modifier = Modifier,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    topBar: @Composable (current: NavDestination) -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    builder: NavGraphBuilder.() -> Unit = {}
) {
    var currentDestination by remember { mutableStateOf(navController.currentDestination) }

    LaunchedEffect(navController) {
        navController
            .currentBackStackEntryFlow
            .collect { backStackEntry -> currentDestination = backStackEntry.destination }
    }

    Scaffold(
        topBar = { currentDestination?.let { topBar(it) } },
        bottomBar = bottomBar,
        contentWindowInsets = contentWindowInsets,
        modifier = modifier
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = startRoute,
            modifier = Modifier.padding(contentPadding),
            builder = { apply(builder) }
        )
    }
}
