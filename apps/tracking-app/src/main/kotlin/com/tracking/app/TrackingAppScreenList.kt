package com.tracking.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tracking.app.ui.flows.daySummaryScreen
import com.tracking.app.ui.flows.weekListSummaryScreen
import com.tracking.app.ui.flows.weekSummaryScreen

fun NavGraphBuilder.trackingAppScreenList(navController: NavController) {
    daySummaryScreen()

    weekListSummaryScreen(navController)
    weekSummaryScreen(navController)
}
