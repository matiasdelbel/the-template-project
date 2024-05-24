package com.tracking.app.ui.flows

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val WeekSummaryScreenRoute = "track/week"

fun NavGraphBuilder.weekSummaryScreen(navController: NavController) = composable(route = WeekSummaryScreenRoute) {
    WeekSummaryScreen(weekNumber = 1, onClick = { navController.navigate(DaySummaryScreenRoute) })
}