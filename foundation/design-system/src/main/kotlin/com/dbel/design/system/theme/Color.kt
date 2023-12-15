package com.dbel.design.system.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

interface DayNightColorScheme {

    fun lightColorScheme(): ColorScheme

    fun darkColorScheme(): ColorScheme
}

@Stable
object PurpleColorScheme : DayNightColorScheme {

    private val Purple40 = Color(color = 0xFF6650a4)
    private val Purple80 = Color(color = 0xFFD0BCFF)

    override fun lightColorScheme(): ColorScheme = lightColorScheme(
        primary = Purple40,
    )

    override fun darkColorScheme(): ColorScheme = darkColorScheme(
        primary = Purple80,
    )

}

@Stable
object LimeColorScheme : DayNightColorScheme {

    private val Lime10 = Color(color = 0xFFF0F4C3)
    private val Lime50 = Color(color = 0xFFCDDC39)
    private val Lime80 = Color(color = 0xFF9E9D24)

    override fun lightColorScheme(): ColorScheme = lightColorScheme(
        primary = Lime50,
        primaryContainer = Lime10,
        surfaceVariant = Lime10
    )

    override fun darkColorScheme(): ColorScheme = darkColorScheme(
        primary = Lime80,
    )
}