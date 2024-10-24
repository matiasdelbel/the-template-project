package com.tracking.app.ui.workout

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
import com.tracking.app.ui.workout.create.CreateWorkoutScreen
import com.tracking.app.ui.workout.list.WorkoutListScreen
import com.tracking.app.ui.workout.update.UpdateWorkoutScreen

internal fun NavGraphBuilder.workoutScreens(navController: NavController) {
    composable(route = CreateWorkoutScreen) {
        CreateWorkoutScreen(
            onClose = { navController.navigateUp() },
            viewModel = hiltViewModel()
        )
    }

    composable(route = HistoricScreen) {
        WorkoutListScreen(
            viewModel = hiltViewModel(),
            onCreateWorkout = { navController.navigate(route = CreateWorkoutScreen) },
            onUpdateWorkout = { workoutId ->
                val updateRunScreen = UpdateRunScreen.replace(oldValue = "{workoutId}", newValue = workoutId.toString())
                navController.navigate(route = updateRunScreen)
            },
            modifier = Modifier
                .padding(top = AppTheme.spacers.md)
                .fillMaxSize()
        )
    }

    composable(
        route = UpdateRunScreen,
        arguments = listOf(navArgument(name = "workoutId") { type = NavType.LongType })
    ) {
        UpdateWorkoutScreen(
            onClose = { navController.navigateUp() },
            viewModel = hiltViewModel()
        )
    }
}

private const val CreateWorkoutScreen = "workout/running/create"
private const val HistoricScreen = "workout/running/historic"
private const val UpdateRunScreen = "workout/running/update/{workoutId}"

const val HomeScreenRoute = HistoricScreen
