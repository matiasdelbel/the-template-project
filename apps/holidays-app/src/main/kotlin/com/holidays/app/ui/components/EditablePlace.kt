package com.holidays.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.dbel.design.system.theme.AppTheme
import com.holidays.app.R

@Composable
fun EditablePlace(
    place: @Composable () -> Unit,
    nameField: @Composable () -> Unit,
    latitudeField: @Composable RowScope.() -> Unit,
    longitudeField: @Composable RowScope.() -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier) {

    Card { place() }

    nameField()

    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        modifier = Modifier
            .padding(top = AppTheme.spacers.sm)
            .fillMaxWidth()
    ) {
        latitudeField()
        longitudeField()
    }

    Button(
        onClick = onSave,
        content = { Text(text = "Save") },
        modifier = Modifier
            .padding(top = AppTheme.spacers.sm)
            .align(Alignment.End)
    )
}

@Composable
fun PlaceName(
    field: TextFieldValue,
    onUpdate: (field: TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    hasError: Boolean = false,
) {
    OutlinedTextField(
        value = field,
        isError = hasError,
        onValueChange = { onUpdate(it) },
        label = { Text(text = "Name") },
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(text = "Zaandam") },
    )
}


@Composable
fun CoordinateTextFiled(
    label: String,
    placeholder: String,
    field: TextFieldValue,
    onFieldChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) = OutlinedTextField(
    isError = isError,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
    label = { Text(text = label) },
    leadingIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_explore),
            contentDescription = null,
        )
    },
    modifier = modifier,
    onValueChange = { onFieldChange(it) },
    placeholder = { Text(text = placeholder) },
    suffix = { Text(text = "°") },
    value = field,
)