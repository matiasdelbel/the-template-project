package com.template.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme
import com.template.showcase.ui.pane.AppSelector
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppTheme { AppContent() } }
    }
}

@Composable
private fun AppContent() = AppSelector()

@Preview(showBackground = true)
@Composable
private fun AppPreview() = AppTheme { AppContent() }
