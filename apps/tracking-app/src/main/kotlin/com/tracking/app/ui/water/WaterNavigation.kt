package com.tracking.app.ui.water

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dbel.design.system.theme.AppTheme

internal fun NavGraphBuilder.waterScreens(navController: NavController) {
    composable(route = CreateWaterScreen) {
        CreateWaterScreen(
            onClose = { navController.navigateUp() },
            viewModel = hiltViewModel()
        )
    }

    composable(route = HistoricScreen) {
        WaterListScreen(
            viewModel = hiltViewModel(),
            onCreateWorkout = { navController.navigate(route = CreateWaterScreen) },
            onUpdateWorkout = { workoutId ->
                val updateRunScreen = UpdateWaterScreen.replace(oldValue = "{waterId}", newValue = workoutId.toString())
                navController.navigate(route = updateRunScreen)
            },
            modifier = Modifier
                .padding(top = AppTheme.paddings.medium)
                .fillMaxSize()
        )
    }

    composable(
        route = UpdateWaterScreen,
        arguments = listOf(navArgument(name = "waterId") { type = NavType.LongType })
    ) {
        UpdateWaterScreen(
            onClose = { navController.navigateUp() },
            viewModel = hiltViewModel()
        )
    }
}

private const val CreateWaterScreen = "water/create"
private const val HistoricScreen = "water/historic"
private const val UpdateWaterScreen = "water/update/{waterId}"
