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
import com.dbel.design.system.theme.AppTheme
import com.rijks.app.ui.RijksHomeScreen
import com.showcase.app.R
import com.tmdb.app.ui.home.TMdbHomeScreen
import com.tracking.app.ui.home.TrackingHomeScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AppSelector(
    onRijksAppSelected: () -> Unit,
    onTmDbAppSelected: () -> Unit,
    onTrackingAppSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 3 })

    Scaffold(
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .add(insets = WindowInsets(left = AppTheme.paddings.medium, right = AppTheme.paddings.medium)),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.fillMaxSize(),
    ) { contentPadding ->
        HorizontalPager(state = pagerState, contentPadding = contentPadding) { page ->
            when (page) {
                0 -> AppPreview(
                    title = stringResource(R.string.rijks_app_name),
                    description = stringResource(id = R.string.rijks_app_description),
                    launch = { onRijksAppSelected() },
                    modifier = Modifier.padding(vertical = AppTheme.paddings.medium, horizontal = AppTheme.paddings.small),
                    content = { Card(elevation = CardDefaults.elevatedCardElevation()) { RijksHomeScreen() } }
                )
                1 -> AppPreview(
                    title = stringResource(R.string.tmdb_app_title),
                    description = stringResource(id = R.string.tmdb_app_description),
                    launch = { onTmDbAppSelected() },
                    modifier = Modifier.padding(vertical = AppTheme.paddings.medium, horizontal = AppTheme.paddings.small),
                    content = { Card(elevation = CardDefaults.elevatedCardElevation()) { TMdbHomeScreen() } }
                )
                2 -> AppPreview(
                    title = stringResource(R.string.tracking_app_name),
                    description = stringResource(id = R.string.tracking_app_description),
                    launch = { onTrackingAppSelected() },
                    modifier = Modifier.padding(vertical = AppTheme.paddings.medium, horizontal = AppTheme.paddings.small),
                    content = { Card(elevation = CardDefaults.elevatedCardElevation()) { TrackingHomeScreen() } }
                )
            }
        }
    }
}

@Composable
private fun AppPreview(
    title: String,
    description: String,
    launch: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)

    Text(
        text = description,
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

    content()
}
