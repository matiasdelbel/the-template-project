package com.holidays.app.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import com.dbel.design.system.theme.AppTheme
import com.holidays.app.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Place(
    name: String,
    latitude: String,
    longitude: String,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
    onUpdate: () -> Unit = {},
) {
    var isEditionSheetOpen by rememberSaveable { mutableStateOf(false) }
    val haptics = LocalHapticFeedback.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .combinedClickable(
                onClick = { },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    isEditionSheetOpen = true
                },
            ),
    ) {
        Column(modifier = Modifier.weight(0.8f)) {
            Text(
                text = name,
                style = AppTheme.typography.titleMedium,
            )

            Row(modifier = Modifier.padding(top = AppTheme.spacers.xs)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_explore),
                    contentDescription = null,
                    modifier = Modifier.padding(end = AppTheme.spacers.xs)
                )
                Text(text = "Latitude $latitude\nLongitude $longitude")
            }
        }
    }

    if (isEditionSheetOpen) EditionSheet(
        onDismissRequest = {
            isEditionSheetOpen = false
        },
        onDelete = {
            onDelete()
            isEditionSheetOpen = false
        },
        onUpdate = {
            onUpdate()
            isEditionSheetOpen = false
        },
    )
}
