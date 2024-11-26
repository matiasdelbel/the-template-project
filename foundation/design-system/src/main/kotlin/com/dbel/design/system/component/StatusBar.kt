package com.dbel.design.system.component

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.dbel.design.system.theme.AppTheme

@Composable
fun PrimaryStatusBar(darkTheme: Boolean) {
    val view = LocalView.current
    val colorScheme = AppTheme.colorScheme

    SideEffect {
        val window = (view.context as Activity).window

        window.statusBarColor = colorScheme.primary.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
}

@Composable
fun SurfaceStatusBar(darkTheme: Boolean) {
    val view = LocalView.current
    val colorScheme = AppTheme.colorScheme

    SideEffect {
        val window = (view.context as Activity).window

        window.statusBarColor = colorScheme.surface.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }
}
