package com.template.showcase.ui.pane

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.theme.BrownColorScheme
import com.dbel.design.system.theme.IndigoColorScheme
import com.dbel.design.system.theme.LimeColorScheme
import com.dbel.design.system.theme.PinkColorScheme
import com.dbel.design.system.theme.TealColorScheme
import com.dbel.design.system.theme.colorScheme
import kotlin.collections.map

@ExperimentalMaterial3Api
@Composable
fun ColorSchemePickerModal(
    sheetState: SheetState,
    selected: ColorScheme,
    onUpdate: (color: ColorScheme) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { },
        sheetState = sheetState,
        content = { ColorSchemePicker(selected, onUpdate) }
    )
}

@ExperimentalMaterial3Api
@Composable
fun ColorSchemePicker(
    selected: ColorScheme,
    onUpdate: (selected: ColorScheme) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(all = AppTheme.spacers.md)
    ) {
        Text(
            text = "Color scheme",
            style = AppTheme.typography.titleLarge,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.spacers.md)
                .scrollable(rememberScrollState(), orientation = Orientation.Horizontal)
        ) {
            listOf(
                PinkColorScheme,
                LimeColorScheme,
                BrownColorScheme,
                IndigoColorScheme,
                TealColorScheme,
            )
                .map { it.colorScheme(isDark = isSystemInDarkTheme()) }
                .forEach { color ->
                    Surface(
                        color = color.primary,
                        modifier = Modifier
                            .size(AppTheme.spacers.xl)
                            .clip(RoundedCornerShape(AppTheme.spacers.sm))
                    ) {
                        Checkbox(
                            checked = selected == color,
                            onCheckedChange = { onUpdate(color) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Transparent,
                                uncheckedColor = Color.Transparent,
                            )
                        )
                    }
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
private fun ColorSchemePickerPreview() {
    AppTheme {
        ColorSchemePicker(selected = PinkColorScheme.lightColorScheme()) { }
    }
}
