package com.tracking.app.ui.flows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme
import com.tracking.app.ui.components.Date
import com.tracking.app.ui.components.DaySummary
import com.tracking.app.ui.components.FlexibilityTrackRow
import com.tracking.app.ui.components.FruitsTrackRow
import com.tracking.app.ui.components.WaterTrackRow
import java.time.LocalDate
import kotlin.random.Random

@Composable
fun DaySummaryScreen(modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(all = AppTheme.paddings.md)
) {
    var showEdition by remember { mutableStateOf(value = false) }

    Date(
        day = LocalDate.now(),
        modifier = Modifier.padding(bottom = AppTheme.paddings.xs)
    )
    DaySummary(
        flexibility = { FlexibilityTrackRow(minutes = Random.nextInt(until = 50), error = true, showEdition = showEdition) },
        fruits = { FruitsTrackRow(pieces = Random.nextInt(until = 3), error = false, showEdition = showEdition) },
        water = { WaterTrackRow(bottles = Random.nextInt(until = 6), error = false, showEdition = showEdition)  },
    )

    if (showEdition) Button(
        onClick = { showEdition = false },
        modifier = Modifier.padding(top = AppTheme.paddings.md).fillMaxWidth()
    ) { Text(text = "Save") }
    else TextButton(
        onClick = { showEdition = true },
        modifier = Modifier.padding(top = AppTheme.paddings.md).fillMaxWidth()
    ) { Text(text = "Edit") }
}
