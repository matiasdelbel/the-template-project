package com.template.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme
import com.tracking.app.ui.TrackingAppContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppTheme { AppContent() } }
    }
}

/**
 * The main composable function from the app using the container.
 */
@Composable
private fun AppContent() = TrackingAppContent()

@Preview(showBackground = true)
@Composable
private fun AppPreview() = AppTheme { AppContent() }
