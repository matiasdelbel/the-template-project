package com.tracking.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.app.container.contract.AppScreens
import com.tracking.app.ui.flows.WeekListSummaryScreenRoute
import com.tracking.app.ui.flows.daySummaryScreen
import com.tracking.app.ui.flows.weekListSummaryScreen
import com.tracking.app.ui.flows.weekSummaryScreen
import com.tracking.app.ui.home.TrackingHomeScreen
import com.tracking.app.ui.profile.profileScreen
import com.tracking.app.ui.running.runningHistoricScreen

object TrackingScreens : AppScreens {

    override val startRoute: String = WeekListSummaryScreenRoute

    override val topRoutes: List<String> = listOf(
        WeekListSummaryScreenRoute,
    )

    @Composable
    override fun Home() = TrackingHomeScreen()
}

fun NavGraphBuilder.trackingScreens(navController: NavController) {
    daySummaryScreen()

    weekListSummaryScreen(navController)
    weekSummaryScreen(navController)

    profileScreen()

    runningHistoricScreen()
}