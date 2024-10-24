package com.tracking.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dbel.design.system.theme.AppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
internal fun DaySummary(
    flexibility: @Composable () -> Unit,
    fruits: @Composable () -> Unit,
    water: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    highlight: Boolean = false,
    colors: DaySummaryColors = DaySummaryDefaults.colors()
) {
    val containerColor by colors.containerColor(highlight = highlight)

    Surface(color = containerColor, modifier = modifier) {
        Column {
            flexibility()
            fruits()
            water()
        }
    }
}

@Composable
internal fun Date(day: LocalDate, modifier: Modifier = Modifier) = Text(
    text = day.format(DaySummaryDefaults.DatePattern),
    style = AppTheme.typography.headlineSmall,
    modifier = modifier,
)

@Immutable
internal class DaySummaryColors(
    private val containerColor: Color,
    private val containerHighlightColor: Color,
) {

    @Composable
    internal fun containerColor(highlight: Boolean): State<Color> = rememberUpdatedState(
        when {
            highlight -> containerColor
            else -> containerHighlightColor
        }
    )
}

@Stable
internal object DaySummaryDefaults {

    val DatePattern = DateTimeFormatter.ofPattern("EEEE, dd MMM")

    @Composable
    fun colors() = DaySummaryColors(
        containerColor = AppTheme.colorScheme.background,
        containerHighlightColor = AppTheme.colorScheme.surface,
    )
}
