package com.tracking.app.ui.running

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dbel.design.system.theme.AppTheme
import com.tracking.app.R

@Composable
fun HistoricScreen(
    viewModel: HistoricViewModel,
    modifier: Modifier = Modifier,
) {
    val workouts = viewModel.all.collectAsState(initial = emptyList())

    LazyColumn(modifier) {
        items(items = workouts.value) {
            Row {
                Column(modifier = Modifier.weight(0.8f)) {
                    Text(
                        text = it.date,
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Row(modifier = Modifier.padding(top = AppTheme.paddings.extraSmall)) {
                        TextWithIcon(
                            text = it.duration,
                            painter = painterResource(id = R.drawable.ic_time),
                            modifier = Modifier.padding(end = AppTheme.paddings.medium)
                        )

                        TextWithIcon(
                            text = it.averagePace,
                            painter = painterResource(id = R.drawable.ic_average_time),
                        )
                    }
                }
                Text(
                    text = it.distanceKm + " km",
                )
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

@Composable
@Preview
fun HistoricScreenPreview() = AppTheme {
    // HistoricScreen()
}