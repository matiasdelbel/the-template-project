package com.dbel.design.system.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme

@Composable
fun Form(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
) = Column(
    modifier = modifier.verticalScroll(state = rememberScrollState()),
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = AppTheme.paddings.medium),
    )

    content()

    Box(
        modifier = Modifier.padding(top = AppTheme.paddings.medium)
    ) {
        footer()
    }
}

object Form {

    @Composable
    fun FooterButton(
        text: String,
        onClick: () -> Unit,
    ) = Button(
        onClick = { onClick() },
        content = { Text(text = text) }
    )
}