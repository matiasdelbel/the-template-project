package com.dbel.design.system.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dbel.design.system.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {},
    isNavigationIconVisible: Boolean = false,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors()
) = TopAppBar(
    title = { Text(text = title) },
    navigationIcon = {
        if (isNavigationIconVisible) IconButton(onClick = { onNavigateUp() }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.close),
            )
        }
    },
    colors = colors,
    modifier = modifier
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarDefaults.primaryTopAppBarColors() = topAppBarColors(
    containerColor = MaterialTheme.colorScheme.primary,
    titleContentColor = MaterialTheme.colorScheme.onPrimary,
    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
)
