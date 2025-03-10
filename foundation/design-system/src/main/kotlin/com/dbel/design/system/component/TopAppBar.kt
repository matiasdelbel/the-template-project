/**
 * This file contains the TopAppBar implementation
 * Check the official documentation for more details https://m3.material.io/components/top-app-bar/overview
 */
package com.dbel.design.system.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalMaterial3Api
@Composable
fun MainTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
    )
}

@ExperimentalMaterial3Api
@Composable
fun SubPageTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavIcon: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavIcon) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MainTopAppBarPreview() {
    MainTopAppBar(title = "Main TopAppBar")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SubPageTopAppBarPreview() {
    SubPageTopAppBar(title = "Sub page TopAppBar") { }
}
