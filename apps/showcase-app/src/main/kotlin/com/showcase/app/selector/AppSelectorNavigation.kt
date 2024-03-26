package com.showcase.app.selector

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal fun NavGraphBuilder.appSelectorScreen(navController: NavController) =
    composable(route = AppSelectorRoute) { AppSelector(navController) }

internal const val AppSelectorRoute = "app_selector"
