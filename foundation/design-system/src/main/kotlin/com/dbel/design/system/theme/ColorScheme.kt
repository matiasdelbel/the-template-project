package com.dbel.design.system.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.dbel.design.system.theme.BrownColorScheme.Brown10
import com.dbel.design.system.theme.IndigoColorScheme.Indigo10
import com.dbel.design.system.theme.LimeColorScheme.Lime10
import com.dbel.design.system.theme.PinkColorScheme.Pink10
import com.dbel.design.system.theme.TealColorScheme.Teal10

interface DayNightColorScheme {

    fun lightColorScheme(): ColorScheme

    fun darkColorScheme(): ColorScheme
}

fun DayNightColorScheme.colorScheme(isDark: Boolean): ColorScheme = if (isDark) darkColorScheme() else lightColorScheme()

@Stable
object BrownColorScheme : DayNightColorScheme {

    private val Brown10 = Color(color = 0xFFD7CCC8)
    private val Brown50 = Color(color = 0xff795548)
    private val Brown80 = Color(color = 0xFF4E342E)

    override fun lightColorScheme(): ColorScheme = lightColorScheme(
        primary = Brown50,
        primaryContainer = Brown10,
        secondaryContainer = Brown10,
        surfaceVariant = Brown10,
    )

    override fun darkColorScheme(): ColorScheme = darkColorScheme(
        primary = Brown80,
    )
}

@Stable
object IndigoColorScheme : DayNightColorScheme {

    private val Indigo10 = Color(color = 0xFFC5CAE9)
    private val Indigo50 = Color(color = 0xff3F51B5)
    private val Indigo80 = Color(color = 0xFF283593)

    override fun lightColorScheme(): ColorScheme = lightColorScheme(
        primary = Indigo50,
        primaryContainer = Indigo10,
        secondaryContainer = Indigo10,
        surfaceVariant = Indigo10,
    )

    override fun darkColorScheme(): ColorScheme = darkColorScheme(
        primary = Indigo80,
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
        secondaryContainer = Lime10,
        surfaceVariant = Lime10,
    )

    override fun darkColorScheme(): ColorScheme = darkColorScheme(
        primary = Lime80,
    )
}

@Stable
object PinkColorScheme : DayNightColorScheme {

    private val Pink10 = Color(color = 0xFFF8BBD0)
    private val Pink50 = Color(color = 0xffE91E63)
    private val Pink80 = Color(color = 0xFFAD1457)

    override fun lightColorScheme(): ColorScheme = lightColorScheme(
        primary = Pink50,
        primaryContainer = Pink10,
        secondaryContainer = Pink10,
        surfaceVariant = Pink10,
    )

    override fun darkColorScheme(): ColorScheme = darkColorScheme(
        primary = Pink80,
    )
}

@Stable
object PurpleColorScheme : DayNightColorScheme {

    private val Purple05 = Color(color = 0xFFF3E5F5)
    private val Purple40 = Color(color = 0xFF6650a4)
    private val Purple80 = Color(color = 0xFFD0BCFF)

    override fun lightColorScheme(): ColorScheme = lightColorScheme(
        primary = Purple40,
        primaryContainer = Purple05,
        secondaryContainer = Purple05,
        surfaceVariant = Purple05,
    )

    override fun darkColorScheme(): ColorScheme = darkColorScheme(
        primary = Purple80,
    )
}

@Stable
object TealColorScheme : DayNightColorScheme {

    private val Teal10 = Color(color = 0xFFB2DFDB)
    private val Teal50 = Color(color = 0xff009688)
    private val Teal80 = Color(color = 0xFF00695C)

    override fun lightColorScheme(): ColorScheme = lightColorScheme(
        primary = Teal50,
        primaryContainer = Teal10,
        secondaryContainer = Teal10,
        surfaceVariant = Teal10,
    )

    override fun darkColorScheme(): ColorScheme = darkColorScheme(
        primary = Teal80,
    )
}
