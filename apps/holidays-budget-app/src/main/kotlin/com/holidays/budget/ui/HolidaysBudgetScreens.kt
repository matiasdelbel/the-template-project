package com.holidays.budget.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.app.container.contract.AppScreens
import com.holidays.budget.ui.example.exampleScreen
import com.holidays.budget.ui.home.HomeRoute
import com.holidays.budget.ui.home.HomeScreen
import com.holidays.budget.ui.home.homeScreen

object HolidaysBudgetScreens : AppScreens {

    override val startRoute: String = HomeRoute

    override val topRoutes: List<String> = listOf(
        HomeRoute,
    )

    @Composable
    override fun Home() = HomeScreen()
}

fun NavGraphBuilder.holidayBudgetScreens(navController: NavController) {
    homeScreen()
    exampleScreen()
}
