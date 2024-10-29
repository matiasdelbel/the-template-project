package com.showcase.app.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme
import com.holidays.app.ui.home.HolidaysHome
import com.tracking.app.ui.TrackingAppContent
import com.tmdb.app.ui.home.TMdbHome
import com.rijks.app.ui.HomeScreen as RijksHomeScreen

@Composable
fun AppPreview(
    page: Int,
    darkColorScheme: ColorScheme,
    lightColorScheme: ColorScheme,
    modifier: Modifier = Modifier
) {
    AppTheme(
        darkColorScheme = darkColorScheme,
        lightColorScheme = lightColorScheme,
        statusBarColor = {},
    ) {
        when (page) {
            0 -> RijksHomeScreen(modifier)
            1 -> TMdbHome(modifier)
            2 -> TrackingAppContent(modifier)
            3 -> HolidaysHome(modifier)
            else -> error("Invalid page: $page")
        }
    }
}
