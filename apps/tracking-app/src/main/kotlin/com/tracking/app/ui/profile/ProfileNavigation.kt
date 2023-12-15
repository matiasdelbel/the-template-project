package com.tracking.app.ui.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ProfileScreenRoute = "profile"

internal fun NavGraphBuilder.profileScreen() =
    composable(route = ProfileScreenRoute) { ProfileScreen() }