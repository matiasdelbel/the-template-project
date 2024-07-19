package com.tracking.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dbel.design.system.theme.AppTheme
import com.tracking.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditionSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
    onUpdate: () -> Unit = {}
) = ModalBottomSheet(modifier = modifier, onDismissRequest = onDismissRequest) {
    Column {
        Text(
            text = "Update workout",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = AppTheme.paddings.medium)
                .padding(horizontal = AppTheme.paddings.medium)
        )

        TextButton(
            onClick = { onUpdate() },
            modifier = Modifier
                .padding(top = AppTheme.paddings.small)
                .padding(horizontal = AppTheme.paddings.medium)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "",
                    modifier = Modifier.padding(end = AppTheme.paddings.small)
                )
                Text(text = "Update")
            }
        }

        TextButton(
            onClick = { onDelete() },
            modifier = Modifier
                .padding(bottom = AppTheme.paddings.medium)
                .padding(horizontal = AppTheme.paddings.medium)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(end = AppTheme.paddings.small)
                )
                Text(
                    text = "Delete",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
