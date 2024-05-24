package com.showcase.app.selector

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.dbel.design.system.theme.AppTheme
import com.showcase.app.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AppSelector(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 5 })
    val pages = listOf(HolidaysBudgetScreens, RijksScreens, TmdbScreens, TrackingScreens, TriominosScreens)

    Scaffold(
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .add(insets = WindowInsets(left = AppTheme.paddings.medium, right = AppTheme.paddings.medium)),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.fillMaxSize(),
    ) { contentPadding ->
        HorizontalPager(state = pagerState, contentPadding = contentPadding) { page ->
            val currentPage = pages[page]
            AppPreview(
                page = page,
                launch = { navController.navigate(currentPage.startRoute) },
                content = { currentPage.Home() },
            )
        }
    }
}

@Composable
private fun AppPreview(
    page: Int,
    launch: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier.padding(vertical = AppTheme.paddings.medium, horizontal = AppTheme.paddings.small)
) {
    Text(
        text = when (page) {
            0 -> stringResource(id = R.string.holidays_budget_app_name)
            1 -> stringResource(id = R.string.rijks_app_name)
            2 -> stringResource(id = R.string.tmdb_app_name)
            3 -> stringResource(id = R.string.tracking_app_name)
            4 -> stringResource(id = R.string.triominos_app_name)
            else -> ""
        },
        style = MaterialTheme.typography.titleMedium
    )

    Text(
        text = when (page) {
            0 -> stringResource(id = R.string.holidays_budget_app_description)
            1 -> stringResource(id = R.string.rijks_app_description)
            2 -> stringResource(id = R.string.tmdb_app_description)
            3 -> stringResource(id = R.string.tracking_app_description)
            4 -> stringResource(id = R.string.triominos_app_description)
            else -> ""
        },
        modifier = Modifier.padding(top = AppTheme.paddings.small)
    )

    Row {
        TextButton(onClick = { launch() }) {
            Text(text = stringResource(id = R.string.app_selector_launch))
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.app_selector_colors))
        }
    }

    Card(elevation = CardDefaults.elevatedCardElevation()) { content() }
}
