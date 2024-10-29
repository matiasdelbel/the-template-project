package com.showcase.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dbel.design.system.theme.AppTheme
import com.showcase.app.R

@Composable
fun AppHeader(
    page: Int,
    onColors: () -> Unit,
    onLaunch: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier) {
    Text(
        text = when (page) {
            0 -> stringResource(id = R.string.rijks_app_name)
            1 -> stringResource(id = R.string.tmdb_app_name)
            2 -> stringResource(id = R.string.tracking_app_name)
            3 -> stringResource(id = R.string.holidays_app_name)
            else -> error("Invalid page: $page")
        },
        style = AppTheme.typography.titleMedium
    )

    Text(
        text = when (page) {
            0 -> stringResource(id = R.string.rijks_app_description)
            1 -> stringResource(id = R.string.tmdb_app_description)
            2 -> stringResource(id = R.string.tracking_app_description)
            3 -> stringResource(id = R.string.holidays_app_description)
            else -> error("Invalid page: $page")
        },
        modifier = Modifier.padding(top = AppTheme.spacers.sm)
    )

    Row {
        TextButton(onClick = onLaunch) { Text(text = stringResource(id = R.string.app_selector_launch)) }
        TextButton(onClick = onColors) { Text(text = stringResource(id = R.string.app_selector_colors)) }
    }
}