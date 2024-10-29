package com.holidays.app.ui.trip.update

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme

@Composable
fun UpdateTrip(
    viewModel: UpdateTripViewModel,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier) {
    if (viewModel.screenState.finish) { onClose() }

    TextField(
        value = viewModel.nameInputValue,
        isError = viewModel.nameInputError,
        onValueChange = { viewModel.updateName(it) },
        label = { Text(text = "Trip name") },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Chipre 2024") },
    )

    Button(
        onClick = { viewModel.updateTrip() },
        content = { Text(text = "Save") },
        modifier = Modifier
            .padding(top = AppTheme.spacers.sm)
            .align(Alignment.End)
    )
}
