package com.showcase.app.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.theme.BrownColorScheme
import com.dbel.design.system.theme.IndigoColorScheme
import com.dbel.design.system.theme.LimeColorScheme
import com.dbel.design.system.theme.PinkColorScheme
import com.dbel.design.system.theme.TealColorScheme

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
            .padding(horizontal = AppTheme.spacers.md)
            .padding(bottom = AppTheme.spacers.xl)
    ) {
        Text(
            text = "Color scheme",
            style = AppTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = AppTheme.spacers.md)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(rememberScrollState(), Orientation.Horizontal)
        ) {
            AvailableColorSchemes.forEach { color ->
                ColorButton(
                    color = color.primary,
                    checked = colorScheme == color,
                    onCheckedChange = { update(color) }
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
            .size(AppTheme.spacers.xl)
            .clip(RoundedCornerShape(AppTheme.spacers.sm))
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

private val AvailableColorSchemes
    @Composable get(): List<ColorScheme> {
        val colorSchemes = listOf(
            PinkColorScheme,
            LimeColorScheme,
            BrownColorScheme,
            IndigoColorScheme,
            TealColorScheme,
        )

        return colorSchemes
            .map { if (isSystemInDarkTheme()) it.darkColorScheme() else it.lightColorScheme() }
            .let { it.toMutableList().apply { add(0, AppTheme.colorScheme) } }
    }
