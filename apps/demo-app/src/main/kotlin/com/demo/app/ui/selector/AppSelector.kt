package com.demo.app.ui.selector

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.demo.app.R
import com.dbel.design.system.theme.AppTheme

@Composable
internal fun AppSelector(
    onRijksAppSelected: () -> Unit,
    onTmDbAppSelected: () -> Unit,
    onTrackingAppSelected: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier.padding(all = AppTheme.paddings.medium)
) {
    Button(
        onClick = { onRijksAppSelected() },
        content = { Text(stringResource(id = R.string.rijks_app_name)) },
        modifier = Modifier.fillMaxWidth()
    )

    Button(
        onClick = { onTmDbAppSelected() },
        content = { Text(stringResource(id = R.string.tmdb_app_name)) },
        modifier = Modifier.fillMaxWidth()
    )

    Button(
        onClick = { onTrackingAppSelected() },
        content = { Text(stringResource(id = R.string.tracking_app_name)) },
        modifier = Modifier.fillMaxWidth()
    )
}
