package com.tracking.app.ui.running

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dbel.design.system.theme.AppTheme

const val HistoricScreenRoute = "running/historic"

fun NavGraphBuilder.runningHistoricScreen() = composable(route = HistoricScreenRoute) {
    HistoricScreen(
        viewModel = hiltViewModel(),
        modifier = Modifier.padding(all = AppTheme.paddings.medium)
    )
}
