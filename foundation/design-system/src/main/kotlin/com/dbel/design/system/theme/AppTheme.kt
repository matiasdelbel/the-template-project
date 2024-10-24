package com.dbel.design.system.theme

import android.app.Activity
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object AppTheme {

    val spacers: Spacers
        @Composable get() = LocalSpacers.current

    val colorScheme: ColorScheme
        @Composable get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable get() = MaterialTheme.typography
}

@Composable
fun AppTheme(
    darkColorScheme: ColorScheme = PurpleColorScheme.darkColorScheme(),
    lightColorScheme: ColorScheme = PurpleColorScheme.lightColorScheme(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Dynamic color is available on Android 12+
    statusBarColor: @Composable () -> Unit = { PrimaryStatusBar(darkTheme) },
    spacers: Spacers = DefaultSpacers,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && SDK_INT >= Build.VERSION_CODES.S -> run {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) { statusBarColor() }

    CompositionLocalProvider(
        LocalSpacers provides spacers
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

@Composable
private fun PrimaryStatusBar(darkTheme: Boolean) {
    val view = LocalView.current
    val colorScheme = MaterialTheme.colorScheme

    SideEffect {
        val window = (view.context as Activity).window

        window.statusBarColor = colorScheme.primary.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
}
