package com.dbel.design.system.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacers(
    val none: Dp = Dp.Unspecified,
    val xxs: Dp = Dp.Unspecified,
    val xs: Dp = Dp.Unspecified,
    val sm: Dp = Dp.Unspecified,
    val md: Dp = Dp.Unspecified,
    val lg: Dp = Dp.Unspecified,
    val xl: Dp = Dp.Unspecified,
)

val LocalSpacers = staticCompositionLocalOf { Spacers() }

internal val DefaultSpacers = Spacers(
    none = 0.dp,
    xxs = 2.dp,
    xs = 4.dp,
    sm = 8.dp,
    md = 16.dp,
    lg = 32.dp,
    xl = 48.dp,
)
