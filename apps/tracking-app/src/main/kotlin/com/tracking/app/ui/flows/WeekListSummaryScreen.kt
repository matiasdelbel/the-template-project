package com.tracking.app.ui.flows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeekListSummaryScreen(
    modifier: Modifier = Modifier,
    onDaySelected: () -> Unit = {},
) {
    val pagerState = rememberPagerState(pageCount = { 10 })
    HorizontalPager(state = pagerState, modifier) { page ->
        WeekSummaryScreen(weekNumber = page + 10, onClick = onDaySelected)
    }
}
