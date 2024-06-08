package com.tracking.app.ui.running

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal fun NavGraphBuilder.recordRunScreen(navController: NavController) = composable(route = RecordRunScreenRoute) {
    RecordRunScreen(
        onFinish = { navController.navigateUp() },
        viewModel = hiltViewModel()
    )
}

internal fun NavController.navigateToRecordRunScreen() = navigate(RecordRunScreenRoute)

internal const val RecordRunScreenRoute = "workout/running/create"
