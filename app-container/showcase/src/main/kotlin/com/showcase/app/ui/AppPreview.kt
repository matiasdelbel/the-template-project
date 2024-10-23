package com.showcase.app.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme
import com.tmdb.app.ui.home.TMdbHomeScreen
import com.tracking.app.ui.TrackingAppContent
import com.holidays.budget.ui.home.HomeScreen as BudgetHomeScreen
import com.rijks.app.ui.HomeScreen as RijksHomeScreen
import com.triominos.home.HomeScreen as TriominosHomeScreen

@Composable
fun AppPreview(
    page: Int,
    lightColorScheme: ColorScheme,
    modifier: Modifier = Modifier
) {
    AppTheme(
        lightColorScheme = lightColorScheme,
        statusBarColor = {},
    ) {
        when (page) {
            0 -> RijksHomeScreen(modifier)
            1 -> TMdbHomeScreen(modifier)
            2 -> TrackingAppContent(modifier)
            3 -> BudgetHomeScreen(modifier)
            4 -> TriominosHomeScreen(modifier)
            else -> error("Invalid page: $page")
        }
    }
}
