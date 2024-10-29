package com.holidays.app.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homePane() = composable(
    route = HolidaysHomeRoute,
    content = { HolidaysHome() }
)

const val HolidaysHomeRoute = "holidays/home"
