package com.triominos.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dbel.design.system.theme.AppTheme
import com.triominos.model.PlayerScore

@Composable
fun Round(
    playerScores: List<PlayerScore>,
    modifier: Modifier = Modifier,
) = Card(modifier) {
    Column(
        modifier = modifier.padding(all = AppTheme.paddings.medium)
    ) {
        val firstPlayer = playerScores[0]
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = firstPlayer.player.name,
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = firstPlayer.score.toString(),
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        val secondPlayer = playerScores[1]
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = secondPlayer.player.name,
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = secondPlayer.score.toString(),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}