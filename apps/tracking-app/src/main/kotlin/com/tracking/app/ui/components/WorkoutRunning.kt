package com.tracking.app.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.dbel.design.system.theme.AppTheme
import com.tracking.app.R

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun WorkoutRunning(
    date: String,
    distanceKm: String,
    duration: String,
    averagePace: String,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
) {
    var onLongPressed by rememberSaveable { mutableStateOf<Boolean>(false) }
    val haptics = LocalHapticFeedback.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .combinedClickable(
                onClick = { },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLongPressed = true
                },
            ),
    ) {
        Column(modifier = Modifier.weight(0.8f)) {
            Text(
                text = date,
                style = MaterialTheme.typography.titleMedium,
            )

            Row(modifier = Modifier.padding(top = AppTheme.paddings.extraSmall)) {
                TextWithIcon(
                    text = duration,
                    painter = painterResource(id = R.drawable.ic_time),
                    modifier = Modifier.padding(end = AppTheme.paddings.medium)
                )

                TextWithIcon(
                    text = averagePace,
                    painter = painterResource(id = R.drawable.ic_average_time),
                )
            }
        }
        Text(
            text = distanceKm,
            fontWeight = FontWeight.Bold
        )
    }

    if (onLongPressed) {
        ModalBottomSheet(onDismissRequest = { onLongPressed = false }) {
            IconButton(
                onClick = { onDelete() },
                modifier = Modifier.fillMaxWidth().padding(all = AppTheme.paddings.medium)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription = "")
                    Text(text = "Delete")
                }
            }
        }
    }
}

@Composable
private fun TextWithIcon(
    text: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) = Row(modifier) {
    Icon(
        painter = painter,
        contentDescription = text,
        modifier = Modifier.padding(end = AppTheme.paddings.extraSmall)
    )
    Text(text = text, style = style)
}
