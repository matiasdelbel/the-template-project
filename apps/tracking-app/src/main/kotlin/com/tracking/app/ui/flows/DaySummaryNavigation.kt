package com.tracking.app.ui.flows

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val DaySummaryScreenRoute = "track/day"

fun NavGraphBuilder.daySummaryScreen() = composable(route = DaySummaryScreenRoute) {
    DaySummaryScreen()
}