package com.holidays.app.ui.trip.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme
import com.holidays.app.R
import com.holidays.app.ui.components.EditionSheet

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Trips(
    viewModel: TripsViewModel,
    onCreate: () -> Unit,
    onSelected: (tripId: Long) -> Unit,
    onUpdate: (workoutId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isEditionSheetOpen by rememberSaveable { mutableStateOf(false) }
    val haptics = LocalHapticFeedback.current
    val trips = viewModel.all.collectAsState(initial = emptyList())

    Box(modifier) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn {
                item {
                    Text(
                        text = "Upcoming trips",
                        style = AppTheme.typography.titleMedium,
                        modifier = Modifier.padding(all = AppTheme.spacers.md)
                    )
                }

                items(items = trips.value) {
                    Text(
                        text = it.name,
                        style = AppTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = AppTheme.spacers.md, vertical = AppTheme.spacers.sm)
                            .combinedClickable(
                                onClick = { onSelected(it.trip.id) },
                                onLongClick = {
                                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                    isEditionSheetOpen = true
                                },
                            ),
                    )

                    if (isEditionSheetOpen) EditionSheet(
                        onDismissRequest = {
                            isEditionSheetOpen = false
                        },
                        onDelete = {
                            viewModel.delete(it)
                            isEditionSheetOpen = false
                        },
                        onUpdate = {
                            onUpdate(it.trip.id)
                            isEditionSheetOpen = false
                        },
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { onCreate() },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "")
        }
    }
}

@Composable
@Preview
fun PlacesScreenPreview() = AppTheme {
    // TODO HistoricScreen()
}