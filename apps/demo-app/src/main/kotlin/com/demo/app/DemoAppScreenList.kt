package com.demo.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.demo.app.ui.selector.appSelectorScreen
import com.rijks.app.ui.ArtCollectionRoute
import com.tmdb.app.ui.PopularMovieCollectionRoute
import com.tracking.app.ui.flows.WeekListSummaryScreenRoute

fun NavGraphBuilder.demoAppScreenList(navController: NavController) {
    appSelectorScreen(
        onRijksAppSelected = { navController.navigate(ArtCollectionRoute) },
        onTmDbAppSelected = { navController.navigate(PopularMovieCollectionRoute) },
        onTrackingAppSelected = { navController.navigate(WeekListSummaryScreenRoute) }
    )
}
