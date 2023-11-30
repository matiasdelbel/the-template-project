package com.demo.app.ui.selector

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal fun NavGraphBuilder.appSelectorScreen(
    onRijksAppSelected: () -> Unit,
    onTmDbAppSelected: () -> Unit,
    onTrackingAppSelected: () -> Unit,
) = composable(route = AppSelectorRoute) {
    AppSelector(onRijksAppSelected, onTmDbAppSelected, onTrackingAppSelected)
}

internal const val AppSelectorRoute = "app_selector"
