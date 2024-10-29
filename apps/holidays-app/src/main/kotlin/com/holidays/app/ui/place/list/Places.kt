package com.holidays.app.ui.place.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme
import com.holidays.app.R
import com.holidays.app.ui.components.Place

@Composable
fun Places(
    viewModel: PlacesViewModel,
    onCreateWorkout: () -> Unit,
    onUpdateWorkout: (workoutId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val workouts = viewModel.all.collectAsState(initial = emptyList())

    Box(modifier) {
        Card {
            LazyColumn {
                items(items = workouts.value) {
                    Place(
                        name = it.name,
                        latitude = it.latitude,
                        longitude = it.longitude,
                        modifier = Modifier.padding(all = AppTheme.spacers.md),
                        onDelete = { viewModel.delete(it) },
                        onUpdate = { onUpdateWorkout(it.place.id) },
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { onCreateWorkout() },
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