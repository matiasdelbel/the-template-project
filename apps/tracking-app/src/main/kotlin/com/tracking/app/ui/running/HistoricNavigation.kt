package com.tracking.app.ui.running

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dbel.design.system.theme.AppTheme

const val HistoricScreenRoute = "running/historic"

fun NavGraphBuilder.historicRunScreen(navController: NavController) = composable(route = HistoricScreenRoute) {
    HistoricScreen(
        viewModel = hiltViewModel(),
        onRecordWorkout = { navController.navigateToRecordRunScreen() },
        modifier = Modifier
            .padding(all = AppTheme.paddings.medium)
            .fillMaxSize()
    )
}
