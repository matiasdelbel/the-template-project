package com.tracking.app.ui.flows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dbel.design.system.theme.AppTheme
import com.tracking.app.R
import com.tracking.app.ui.components.Date
import com.tracking.app.ui.components.DaySummary
import com.tracking.app.ui.components.FlexibilityTrackRow
import com.tracking.app.ui.components.FruitsTrackRow
import com.tracking.app.ui.components.WaterTrackRow
import java.time.LocalDate
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeekSummaryScreen(
    weekNumber: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) = LazyColumn(modifier) {
    stickyHeader { Week(weekNumber, modifier = Modifier.header()) }

    for (numberOfDay in 0L..6) {
        val date = LocalDate.now().plusDays(numberOfDay)

        stickyHeader { Date(day = date, modifier = Modifier.header()) }
        item {
            DaySummary(
                highlight = numberOfDay == 1L,
                flexibility = { FlexibilityTrackRow(minutes = Random.nextInt(until = 50), error = false) },
                fruits = { FruitsTrackRow(pieces = Random.nextInt(until = 3), error = false) },
                water = { WaterTrackRow(bottles = Random.nextInt(until = 6), error = false) },
                modifier = Modifier
                    .clickable { onClick() }
                    .padding(horizontal = AppTheme.paddings.md)
                    .padding(top = AppTheme.paddings.sm)
            )
        }
    }
}

@Composable
private fun Week(weekNumber: Int, modifier: Modifier = Modifier) = Text(
    text = stringResource(R.string.week_summary_number, weekNumber),
    style = MaterialTheme.typography.headlineMedium,
    modifier = modifier
)

@Composable
private fun Modifier.header() = this then Modifier
    .background(MaterialTheme.colorScheme.background)
    .fillMaxWidth()
    .padding(horizontal = AppTheme.paddings.md, vertical = AppTheme.paddings.sm)