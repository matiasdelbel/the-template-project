package com.showcase.app.selector

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.holidays.budget.ui.home.holidayHomeScreen
import com.rijks.app.ui.ArtCollectionRoute
import com.tmdb.app.ui.home.TMdbHomeRoute
import com.tmdb.app.ui.home.TMdbHomeScreen
import com.tmdb.app.ui.popular.PopularMovieCollectionRoute
import com.tracking.app.ui.flows.WeekListSummaryScreenRoute
import com.tracking.app.ui.home.TrackingHomeScreen
import com.triominos.example.exampleScreen
import com.triominos.home.HomeRoute
import com.triominos.home.HomeScreen
import com.triominos.home.homeScreen

interface AppScreens {

    val startRoute: String
    val topRoutes: List<String>

    @Composable
    fun Home()
}

object TriominosScreens : AppScreens {

    override val startRoute: String = HomeRoute

    override val topRoutes: List<String> = listOf(HomeRoute)

    @Composable
    override fun Home() = HomeScreen()
}

object TrackingScreens : AppScreens {

    override val startRoute: String = WeekListSummaryScreenRoute

    override val topRoutes: List<String> = listOf(WeekListSummaryScreenRoute)

    @Composable
    override fun Home() = TrackingHomeScreen()
}

object TmdbScreens : AppScreens {

    override val startRoute: String = TMdbHomeRoute

    override val topRoutes: List<String> = listOf(
        TMdbHomeRoute,
        PopularMovieCollectionRoute,
    )

    @Composable
    override fun Home() = TMdbHomeScreen()
}

object RijksScreens : AppScreens {

    override val startRoute = ArtCollectionRoute

    override val topRoutes = listOf(
        ArtCollectionRoute,
    )

    @Composable
    override fun Home() = com.rijks.app.ui.HomeScreen()
}

object HolidaysBudgetScreens : AppScreens {

    override val startRoute: String = com.holidays.budget.ui.home.HomeRoute

    override val topRoutes: List<String> = listOf(com.holidays.budget.ui.home.HomeRoute)

    @Composable
    override fun Home() = com.holidays.budget.ui.home.HomeScreen()
}

fun NavGraphBuilder.holidayBudgetScreens(navController: NavController) {
    holidayHomeScreen()
    exampleScreen()
}

fun NavGraphBuilder.triominosScreens(navController: NavController) {
    homeScreen()
    exampleScreen()
}
