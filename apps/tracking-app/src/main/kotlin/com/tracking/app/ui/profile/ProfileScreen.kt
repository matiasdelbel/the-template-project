package com.tracking.app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.dbel.design.system.theme.AppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) = Column(modifier = modifier.fillMaxSize().padding(all = AppTheme.spacers.md)) {
    Row(verticalAlignment = Alignment.CenterVertically,) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "",
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(color = AppTheme.colorScheme.secondary)
                .size(AppTheme.spacers.xl)
                .padding(all = AppTheme.spacers.xs)
        )
        Text(
            text = "Matias Del Bel",
            modifier = Modifier
                .padding(start = AppTheme.spacers.sm)
        )
    }

    TextButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth().padding(top = AppTheme.spacers.sm)
    ) {
        Text(text = "Edit profile")
    }

    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "LogIn with Google")
    }
}