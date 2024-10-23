package com.tracking.app.ui.workout.update

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.dbel.design.system.theme.AppTheme
import com.tracking.app.R
import com.tracking.app.ui.components.WorkoutRunning

@Composable
fun UpdateWorkoutScreen(
    viewModel: UpdateWorkoutViewModel,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier) {
    if (viewModel.screenState.finish) {
        onClose()
    }

    WorkoutRunningCard(viewModel)

    DateOutlinedTextField(
        date = viewModel.dateString,
        onDateUpdated = { viewModel.updateDate(it) },
        modifier = Modifier
            .padding(top = AppTheme.paddings.md)
            .fillMaxWidth()
    )

    DistanceOutlinedTextField(
        field = viewModel.distanceKmInputValue,
        isError = viewModel.distanceKmInputError,
        onFieldChange = { viewModel.updateDistance(it) },
        modifier = Modifier
            .padding(top = AppTheme.paddings.sm)
            .fillMaxWidth()
    )

    DurationOutlinedTextField(
        field = viewModel.durationInputFieldState,
        isError = viewModel.durationFieldError,
        onFieldChange = { viewModel.updateDuration(it) },
        modifier = Modifier
            .padding(top = AppTheme.paddings.sm)
            .fillMaxWidth()
    )

    AveragePaceOutlinedTextField(
        field = viewModel.averagePaceInputFieldState,
        isError = viewModel.averagePaceFieldError,
        onFieldChange = { viewModel.updateAveragePace(it) },
        modifier = Modifier
            .padding(top = AppTheme.paddings.sm)
            .fillMaxWidth()
    )

    Button(
        onClick = { viewModel.updateWorkout() },
        content = { Text(text = "Update") },
        modifier = Modifier
            .padding(top = AppTheme.paddings.sm)
            .align(Alignment.End)
    )
}

@Composable
private fun WorkoutRunningCard(
    viewModel: UpdateWorkoutViewModel,
    modifier: Modifier = Modifier,
) = Card(modifier) {
    val averagePace by remember {
        derivedStateOf { viewModel.averagePaceInputFieldState.text.ifBlank { "00:00" } }
    }

    val distanceKm by remember {
        derivedStateOf { "${viewModel.distanceKmInputValue.text.ifBlank { "0,00" }} km" }
    }

    val duration by remember {
        derivedStateOf { viewModel.durationInputFieldState.text.ifBlank { "00:00" } }
    }

    WorkoutRunning(
        averagePace = averagePace,
        date = viewModel.dateString,
        distanceKm = distanceKm,
        duration = duration,
        modifier = Modifier.padding(all = AppTheme.paddings.md)
    )
}

@Composable
private fun DateOutlinedTextField(
    date: String,
    onDateUpdated: (String) -> Unit,
    modifier: Modifier = Modifier,
) = OutlinedTextField(
    label = { Text(text = "Date") },
    value = date,
    readOnly = true,
    onValueChange = { onDateUpdated(it) },
    trailingIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar_month),
                contentDescription = null
            )
        }
    },
    modifier = modifier
)

@Composable
private fun DistanceOutlinedTextField(
    field: TextFieldValue,
    onFieldChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) = OutlinedTextField(
    isError = isError,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
    label = { Text(text = "Distance") },
    modifier = modifier,
    onValueChange = { onFieldChange(it) },
    placeholder = { Text(text = "0.00") },
    suffix = { Text(text = "km") },
    value = field
)

@Composable
private fun DurationOutlinedTextField(
    field: TextFieldValue,
    onFieldChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) = OutlinedTextField(
    isError = isError,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    label = { Text(text = "Duration") },
    leadingIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_time),
            contentDescription = "Duration",
        )
    },
    modifier = modifier,
    onValueChange = { onFieldChange(it) },
    placeholder = { Text(text = "00:00") },
    suffix = { Text(text = "mm:ss") },
    value = field,
)

@Composable
private fun AveragePaceOutlinedTextField(
    field: TextFieldValue,
    onFieldChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) = OutlinedTextField(
    isError = isError,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    label = { Text(text = "Average pace") },
    leadingIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_average_time),
            contentDescription = "Average pace",
        )
    },
    modifier = modifier,
    onValueChange = { onFieldChange(it) },
    placeholder = { Text(text = "00:00") },
    suffix = { Text(text = "mm:ss") },
    value = field,
)
