package com.your.app.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppContent(
    modifier: Modifier = Modifier
) = Surface(
    color = MaterialTheme.colorScheme.background,
    modifier = modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = AppStartDestinationRoute,
            modifier = Modifier.padding(contentPadding)
        ) {
            // TODO Add your app destinations in here
        }
    }
}

private const val AppStartDestinationRoute = "" // TODO update the start destination of your app
