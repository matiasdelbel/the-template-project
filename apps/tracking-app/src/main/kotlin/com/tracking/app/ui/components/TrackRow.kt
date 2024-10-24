package com.tracking.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.dbel.design.system.theme.AppTheme
import com.tracking.app.R

@Composable
private fun TrackRow(
    title: String,
    description: String,
    quantity: String,
    modifier: Modifier = Modifier,
    error: Boolean = false,
    showEdition: Boolean = false,
    colors: TrackRowColors = TrackRowDefaults.colors(),
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier.defaultMinSize(minHeight = AppTheme.spacers.xl)
) {
    val contentColor by colors.contentColor(error = error)

    Column {
        Text(
            text = title,
            color = contentColor,
            style = AppTheme.typography.titleMedium
        )
        Text(
            text = description,
            color = contentColor,
            style = AppTheme.typography.labelSmall
        )
    }

    Spacer(modifier = Modifier.weight(weight = 1f))

    Row(verticalAlignment = Alignment.CenterVertically) {
        if (showEdition) TextButton(onClick = { /*TODO*/ }) { Text(text = "-") }
        Text(text = quantity)
        if (showEdition) TextButton(onClick = { /*TODO*/ }) { Text(text = "+") }
    }
}

@Composable
internal fun FlexibilityTrackRow(
    minutes: Int,
    modifier: Modifier = Modifier,
    error: Boolean = false,
    showEdition: Boolean = false,
) = TrackRow(
    title = stringResource(R.string.day_summary_flexibility),
    description = stringResource(R.string.day_summary_flexibility_caption),
    quantity = stringResource(R.string.day_summary_mins, minutes),
    modifier = modifier,
    error = error,
    showEdition = showEdition,
)

@Composable
internal fun FruitsTrackRow(
    pieces: Int,
    modifier: Modifier = Modifier,
    error: Boolean = false,
    showEdition: Boolean = false,
) = TrackRow(
    title = stringResource(R.string.day_summary_fruits),
    description = stringResource(R.string.day_summary_fruits_caption),
    quantity = stringResource(R.string.day_summary_pieces_of_fruit, pieces),
    modifier = modifier,
    error = error,
    showEdition = showEdition,
)

@Composable
internal fun WaterTrackRow(
    bottles: Int,
    modifier: Modifier = Modifier,
    error: Boolean = false,
    showEdition: Boolean = false,
) = TrackRow(
    title = stringResource(R.string.day_summary_water),
    description = stringResource(R.string.day_summary_water_caption),
    quantity = stringResource(R.string.day_summary_number_of_bottles, bottles),
    modifier = modifier,
    error = error,
    showEdition = showEdition,
)

@Immutable
internal class TrackRowColors(
    private val contentColor: Color,
    private val contentErrorColor: Color,
) {

    @Composable
    internal fun contentColor(error: Boolean): State<Color> = rememberUpdatedState(
        when {
            error -> contentErrorColor
            else -> contentColor
        }
    )
}

@Stable
internal object TrackRowDefaults {

    @Composable
    fun colors() = TrackRowColors(
        contentColor = AppTheme.colorScheme.onSurface,
        contentErrorColor = AppTheme.colorScheme.error,
    )
}
