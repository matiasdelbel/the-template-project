package com.tracking.app.ui.flows

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.weekListSummaryScreen(navController: NavController) =
    composable(route = WeekListSummaryScreenRoute) { WeekListSummaryScreen(onDaySelected = { navController.navigate(DaySummaryScreenRoute) }) }

internal const val WeekListSummaryScreenRoute = "track/weeks"