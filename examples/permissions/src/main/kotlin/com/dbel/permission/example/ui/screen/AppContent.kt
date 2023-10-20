package com.dbel.permission.example.ui.screen

import android.Manifest.permission.CAMERA
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme

@Composable
fun AppContent(
    modifier: Modifier = Modifier
) = Surface(
    color = MaterialTheme.colorScheme.background,
    modifier = modifier.padding(all = AppTheme.paddings.medium),
) {
    Column {
        RequestPermission(
            permission = CAMERA,
            modifier = Modifier.fillMaxWidth()
        )

        RequestMultiplePermission(
            permissions = listOf(CAMERA, CAMERA),
            modifier = Modifier.fillMaxWidth().padding(top = AppTheme.paddings.medium)
        )
    }
}

@Composable
@Preview
fun AppContentPreview() = AppContent()
