package com.triominos.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dbel.design.system.theme.AppTheme

internal const val ExampleRoute = "example"

internal fun NavGraphBuilder.exampleScreen() = composable(route = ExampleRoute) { ExampleScreen() }

@Composable
fun ExampleScreen(
    modifier: Modifier = Modifier
) = Column(modifier = modifier.fillMaxSize().padding(all = AppTheme.paddings.medium)) {
    Text(text = "Triominos")
}