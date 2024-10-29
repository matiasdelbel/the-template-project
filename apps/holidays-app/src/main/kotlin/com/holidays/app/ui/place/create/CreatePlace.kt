package com.holidays.app.ui.place.create

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme
import com.holidays.app.ui.components.CoordinateTextFiled
import com.holidays.app.ui.components.EditablePlace
import com.holidays.app.ui.components.Place
import com.holidays.app.ui.components.PlaceName

@Composable
fun CreatePlace(
    viewModel: CreatePlaceViewModel,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (viewModel.screenState.finish) { onClose() }

    val name by remember { derivedStateOf { viewModel.nameInputValue.text.ifBlank { "Zaandam" } } }
    val latitude by remember { derivedStateOf { viewModel.latitudeInputFieldState.text.ifBlank { "52.442039" } } }
    val longitude by remember { derivedStateOf { viewModel.longitudeInputFieldState.text.ifBlank { "4.829199" } } }

    EditablePlace(
        place = {
            Place(
                latitude = latitude,
                longitude = longitude,
                name = name,
                modifier = Modifier.padding(all = AppTheme.spacers.md)
            )
        },
        nameField = {
            PlaceName(
                field = viewModel.nameInputValue,
                onUpdate = {viewModel.updateName(it) },
                modifier = Modifier.fillMaxWidth(),
            )
        },
        latitudeField = {
            CoordinateTextFiled(
                label = "Latitude",
                placeholder = "52.442039",
                field = viewModel.latitudeInputFieldState,
                onFieldChange = { viewModel.updateLatitude(it) },
                isError = viewModel.latitudeFieldError,
                modifier = Modifier.weight(0.5f)
            )
        },
        longitudeField = {
            CoordinateTextFiled(
                label = "Longitude",
                placeholder = "4.829199",
                field = viewModel.longitudeInputFieldState,
                onFieldChange = { viewModel.updateLongitude(it) },
                isError = viewModel.longitudeFieldError,
                modifier = Modifier.weight(0.5f)
            )
        },
        onSave = { viewModel.savePlace() },
        modifier = modifier,
    )
}
