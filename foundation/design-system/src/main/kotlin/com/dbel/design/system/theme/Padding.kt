package com.dbel.design.system.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Paddings(
    val extraSmall: Dp = Dp.Unspecified,
    val small: Dp = Dp.Unspecified,
    val medium: Dp = Dp.Unspecified,
    val large: Dp = Dp.Unspecified,
)

val LocalPaddings = staticCompositionLocalOf { Paddings() }

internal val DefaultPaddings = Paddings(
    extraSmall = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    large = 32.dp,
)
