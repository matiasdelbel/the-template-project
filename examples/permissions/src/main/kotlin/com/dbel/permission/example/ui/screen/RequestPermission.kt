package com.dbel.permission.example.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(
    permission: String,
    modifier: Modifier = Modifier
) = Card(modifier) {
    val permissionState = rememberPermissionState(permission)

    Column(modifier = modifier.padding(all = AppTheme.paddings.medium)) {
        Text(
            text = permission,
            style = MaterialTheme.typography.titleMedium
        )

        if (permissionState.status.isGranted) {
            Text(
                text = "${permissionState.permission} Granted",
                modifier = modifier.padding(all = AppTheme.paddings.medium)
            )
        } else {
            val textToShow = if (permissionState.status.shouldShowRationale) {
                // If the user has denied the permission but the rationale can be shown,
                // then gently explain why the app requires this permission
                "The camera is important for this app. Please grant the permission."
            } else {
                // If it's the first time the user lands on this feature, or the user
                // doesn't want to be asked again for this permission, explain that the
                // permission is required
                "Camera permission required for this feature to be available. Please grant the permission."
            }
            Text(textToShow)

            Button(
                onClick = { permissionState.launchPermissionRequest() },
                modifier = Modifier.padding(top = AppTheme.paddings.small)
            ) {
                Text("Request permission")
            }
        }
    }
}
