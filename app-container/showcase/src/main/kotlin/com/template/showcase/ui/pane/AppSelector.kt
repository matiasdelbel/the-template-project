package com.template.showcase.ui.pane

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dbel.design.system.theme.AppTheme
import com.template.showcase.R
import kotlinx.coroutines.launch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import com.dbel.design.system.component.MainTopAppBar
import com.holidays.app.ui.home.HolidaysHome
import com.rijks.app.ui.HomeScreen as RijksHomeScreen
import com.tmdb.app.ui.home.TMdbHome
import com.tracking.app.ui.TrackingAppContent

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
internal fun AppSelector(modifier: Modifier = Modifier) {
    val colorScheme = AppTheme.colorScheme
    val appsColorSchemeState = remember {
        mutableStateListOf(colorScheme, colorScheme, colorScheme, colorScheme)
    }
    val appsPagerState = rememberPagerState(pageCount = { appsColorSchemeState.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        containerColor = AppTheme.colorScheme.primaryContainer,
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .add(
                insets = WindowInsets(
                    left = AppTheme.spacers.md,
                    right = AppTheme.spacers.md
                )
            ),
        topBar = { MainTopAppBar(title = stringResource(R.string.app_name)) },
        content = { contentPadding ->
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = appsPagerState,
                contentPadding = contentPadding,
                pageContent = { page ->
                    val colorSchemeModalState = rememberModalBottomSheetState()

                    Column {
                        AppHeader(
                            page = page,
                            onColors = { coroutineScope.launch { colorSchemeModalState.show() } },
                            onLaunch = { TODO() },
                            modifier = Modifier.padding(top = AppTheme.spacers.md)
                        )

                        AppPreview(
                            page = page,
                            darkColorScheme = appsColorSchemeState[page],
                            lightColorScheme = appsColorSchemeState[page],
                            modifier = Modifier
                                .padding(horizontal = AppTheme.spacers.sm)
                                .clip(
                                    RoundedCornerShape(
                                        topStart = AppTheme.spacers.sm,
                                        topEnd = AppTheme.spacers.sm,
                                    )
                                )
                        )
                    }

                    if (colorSchemeModalState.isVisible) ColorSchemePickerModal(
                        sheetState = colorSchemeModalState,
                        selected = appsColorSchemeState[page],
                        onUpdate = { updatedColorScheme -> appsColorSchemeState[page] = updatedColorScheme },
                    )
                }
            )
        }
    )
}

@Composable
fun AppPreview(
    page: Int,
    darkColorScheme: ColorScheme,
    lightColorScheme: ColorScheme,
    modifier: Modifier = Modifier
) {
    AppTheme(
        darkColorScheme = darkColorScheme,
        lightColorScheme = lightColorScheme,
        statusBarColor = {},
    ) {
        when (page) {
            0 -> RijksHomeScreen(modifier)
            1 -> TMdbHome(modifier)
            2 -> TrackingAppContent(modifier)
            3 -> HolidaysHome(modifier)
            else -> error("Invalid page: $page")
        }
    }
}

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
