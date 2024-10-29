package com.holidays.app.ui.trip

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
import com.holidays.app.ui.trip.create.CreateTrip
import com.holidays.app.ui.trip.detail.Trip
import com.holidays.app.ui.trip.list.Trips
import com.holidays.app.ui.trip.update.UpdateTrip

internal fun NavGraphBuilder.tripsPane(navController: NavController) {
    composable(route = TripsPane) {
        Trips(
            onCreate = { navController.navigate(route = CreateTripPane) },
            onUpdate = { tripId -> navController.navigate(route = UpdateTripsPane(tripId = tripId.toString())) },
            onSelected = { tripId -> navController.navigate(route = DetailTripPane(tripId = tripId.toString())) },
            viewModel = hiltViewModel(),
            modifier = Modifier
                .padding(top = AppTheme.spacers.md)
                .fillMaxSize()
        )
    }

    composable(route = CreateTripPane) {
        CreateTrip(
            onClose = { navController.navigateUp() },
            viewModel = hiltViewModel(),
            modifier = Modifier.fillMaxSize()
        )
    }

    composable(
        route = UpdateTripsPane,
        arguments = listOf(navArgument(name = "tripId") { type = NavType.LongType })
    ) {
        UpdateTrip(
            onClose = { navController.navigateUp() },
            viewModel = hiltViewModel(),
            modifier = Modifier.fillMaxSize()
        )
    }

    composable(
        route = DetailTripPane,
        arguments = listOf(navArgument(name = "tripId") { type = NavType.LongType })
    ) {
        Trip(
            viewModel = hiltViewModel(),
            modifier = Modifier.fillMaxSize()
        )
    }
}

const val CreateTripPane = "trip/create"
const val DetailTripPane = "trips/detail/{tripId}"
const val TripsPane = "trips/list"
const val UpdateTripsPane = "trips/update/{tripId}"

private operator fun String.invoke(tripId: String) = replace(oldValue = "{tripId}", newValue = tripId)
