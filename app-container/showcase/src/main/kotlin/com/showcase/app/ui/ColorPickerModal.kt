package com.showcase.app.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.dbel.design.system.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSchemePickerModal(
    colorScheme: ColorScheme,
    update: (color: ColorScheme) -> Unit,
    sheetState: SheetState,
) = ModalBottomSheet(
    onDismissRequest = { },
    sheetState = sheetState,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = AppTheme.paddings.medium)
            .padding(bottom = AppTheme.paddings.extraLarge)
    ) {
        Text(
            text = "Colors",
            style = MaterialTheme.typography.headlineSmall,
        )

        Text(
            text = "Primary",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                top = AppTheme.paddings.extraSmall,
                bottom = AppTheme.paddings.small,
            )
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(rememberScrollState(), Orientation.Horizontal)
        ) {
            listOf(primary, deepOrange, pink, green, teal, lightBlue).forEach { color ->
                ColorButton(
                    color = color,
                    checked = colorScheme.primary == color,
                    onCheckedChange = { update(colorScheme.copy(primary = color)) }
                )
            }
        }
    }
}

@Composable
private fun ColorButton(
    color: Color,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Surface(
        color = color,
        modifier = Modifier
            .size(AppTheme.paddings.extraLarge)
            .clip(RoundedCornerShape(AppTheme.paddings.small))
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Transparent,
                uncheckedColor = Color.Transparent,
            )
        )
    }
}

private val primary @Composable get() = MaterialTheme.colorScheme.primary
private val deepOrange = Color(color = 0xffD84315)
private val pink = Color(color = 0xffE91E63)
private val green = Color(color = 0xff4CAF50)
private val teal = Color(color = 0xFF009688)
private val lightBlue = Color(color = 0xff03A9F4)
