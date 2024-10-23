package com.showcase.app.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
internal fun AppSelector(modifier: Modifier = Modifier) {
    val colorScheme = MaterialTheme.colorScheme
    val appsColorSchemeState = remember {
        mutableStateListOf(colorScheme, colorScheme, colorScheme, colorScheme, colorScheme)
    }
    val appsPagerState = rememberPagerState(pageCount = { appsColorSchemeState.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .add(
                insets = WindowInsets(
                    left = AppTheme.paddings.medium,
                    right = AppTheme.paddings.medium
                )
            ),
        topBar = {
            TopAppBar(
                title = stringResource(R.string.showcase_app_name),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
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
                            lightColorScheme = appsColorSchemeState[page],
                            modifier = Modifier.padding(horizontal = AppTheme.paddings.small)
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
