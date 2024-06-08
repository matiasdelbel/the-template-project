package com.tracking.app.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tracking.app.ui.flows.daySummaryScreen
import com.tracking.app.ui.flows.weekListSummaryScreen
import com.tracking.app.ui.flows.weekSummaryScreen
import com.tracking.app.ui.profile.profileScreen
import com.tracking.app.ui.workout.workoutScreens

fun NavGraphBuilder.trackingScreens(navController: NavController) {
    daySummaryScreen()
    weekListSummaryScreen(navController)
    weekSummaryScreen(navController)
    profileScreen()

    workoutScreens(navController)
}