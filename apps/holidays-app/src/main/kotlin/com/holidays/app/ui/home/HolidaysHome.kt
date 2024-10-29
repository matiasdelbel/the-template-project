package com.holidays.app.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.component.NavHostScaffold
import com.dbel.design.system.component.TopAppBar
import com.holidays.app.R
import com.holidays.app.ui.place.placesPane
import com.holidays.app.ui.trip.TripsPane
import com.holidays.app.ui.trip.tripsPane

@Composable
fun HolidaysHome(modifier: Modifier = Modifier) = Surface(modifier = modifier) {
    val navController = rememberNavController()

    NavHostScaffold(
        navController = navController,
        startRoute = TripsPane,
        contentWindowInsets = WindowInsets(
            left = AppTheme.spacers.sm,
            right = AppTheme.spacers.sm
        ),
        builder = {
            placesPane(navController)
            tripsPane(navController)
        },
        topBar = { TopBar { navController.navigateUp()} },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onNavigateUp: () -> Unit) = TopAppBar(
    title = stringResource(id = R.string.places),
    isNavigationIconVisible = false,
    onNavigateUp = onNavigateUp
)
