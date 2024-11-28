package com.tmdb.app.ui.components

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme

@Composable
fun MovieCollectionPicker(
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onSelected: (index: Int) -> Unit
) = Row(
    horizontalArrangement = Arrangement.spacedBy(space = AppTheme.spacers.sm),
    modifier = modifier
        .focusGroup()
        .fillMaxWidth()
        .horizontalScroll(state = rememberScrollState())
) {
    FilterChip(
        selected = selectedIndex == NowPlayingMoviesIndex,
        onClick = { onSelected(NowPlayingMoviesIndex) },
        label = { Text(text = "Now playing") }
    )

    FilterChip(
        selected = selectedIndex == PopularMoviesIndex,
        onClick = { onSelected(PopularMoviesIndex) },
        label = { Text(text = "Popular") },
    )

    FilterChip(
        selected = selectedIndex == TopRatedMoviesIndex,
        onClick = { onSelected(TopRatedMoviesIndex) },
        label = { Text(text = "Top rated") }
    )

    FilterChip(
        selected = selectedIndex == UpcomingMoviesIndex,
        onClick = { onSelected(UpcomingMoviesIndex) },
        label = { Text(text = "Upcoming") }
    )
}

const val NowPlayingMoviesIndex = 0
const val PopularMoviesIndex = 1
const val TopRatedMoviesIndex = 2
const val UpcomingMoviesIndex = 3
