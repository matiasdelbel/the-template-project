package com.template.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme
import com.tmdb.app.ui.home.TMdbHome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppTheme { AppContent() } }
    }
}

/**
 * Replace the following with your app content.
 */
@Composable
private fun AppContent() = TMdbHome()

@Preview(showBackground = true)
@Composable
private fun AppPreview() = AppTheme { AppContent() }
