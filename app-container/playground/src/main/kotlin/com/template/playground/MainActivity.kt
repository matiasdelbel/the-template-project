package com.template.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme
import com.showcase.app.ShowcaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppTheme { AppContent() } }
    }
}

@Composable
fun AppContent() = ShowcaseApp() // The main composable function from the app using the container.

@Preview(showBackground = true)
@Composable
fun AppPreview() = AppTheme { AppContent() }
