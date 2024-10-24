package com.showcase.app.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dbel.design.system.component.TopAppBar
import com.dbel.design.system.theme.AppTheme
import com.showcase.app.R
import kotlinx.coroutines.launch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
internal fun AppSelector(modifier: Modifier = Modifier) {
    val colorScheme = AppTheme.colorScheme
    val appsColorSchemeState = remember {
        mutableStateListOf(colorScheme, colorScheme, colorScheme)
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
        topBar = {
            TopAppBar(
                title = stringResource(R.string.showcase_app_name),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colorScheme.primaryContainer,
                    titleContentColor = AppTheme.colorScheme.onPrimaryContainer
                ),
            )
        },
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
                            onLaunch = { TODO() }
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
                        colorScheme = appsColorSchemeState[page],
                        update = { updatedColorScheme -> appsColorSchemeState[page] = updatedColorScheme },
                    )
                }
            )
        }
    )
}
