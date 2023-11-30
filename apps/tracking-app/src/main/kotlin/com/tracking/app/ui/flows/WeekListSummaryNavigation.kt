package com.tracking.app.ui.flows

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val WeekListSummaryScreenRoute = "track/weeks"

internal fun NavGraphBuilder.weekListSummaryScreen(navController: NavController) =
    composable(route = WeekListSummaryScreenRoute) { WeekListSummaryScreen(onDaySelected = { navController.navigate(DaySummaryScreenRoute) }) }