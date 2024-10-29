package com.holidays.app.ui.place

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
import com.holidays.app.ui.place.create.CreatePlace
import com.holidays.app.ui.place.list.Places
import com.holidays.app.ui.place.update.UpdatePlace

internal fun NavGraphBuilder.placesPane(navController: NavController) {
    composable(route = PlacesScreen) {
        Places(
            viewModel = hiltViewModel(),
            onCreateWorkout = { navController.navigate(route = CreatePlaceScreen) },
            onUpdateWorkout = { workoutId ->
                val updateRunScreen = UpdatePlacesScreen.replace(oldValue = "{workoutId}", newValue = workoutId.toString())
                navController.navigate(route = updateRunScreen)
            },
            modifier = Modifier
                .padding(top = AppTheme.spacers.md)
                .fillMaxSize()
        )
    }

    composable(route = CreatePlaceScreen) {
        CreatePlace(
            onClose = { navController.navigateUp() },
            viewModel = hiltViewModel(),
            modifier = Modifier.fillMaxSize()
        )
    }

    composable(
        route = UpdatePlacesScreen,
        arguments = listOf(navArgument(name = "workoutId") { type = NavType.LongType })
    ) {
        UpdatePlace(
            onClose = { navController.navigateUp() },
            viewModel = hiltViewModel()
        )
    }
}

const val CreatePlaceScreen = "places/create"
const val PlacesScreen = "places/list"
const val UpdatePlacesScreen = "places/update/{workoutId}"
