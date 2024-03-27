package com.triominos.ui.game

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dbel.design.system.theme.AppTheme
import com.triominos.model.PlayerScore

internal const val GameScreenRoute = "triominos/score"

internal fun NavGraphBuilder.gameScreen() = composable(route = GameScreenRoute) { GameScreen() }

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel
        .uiState
        .collectAsState(initial = GameViewModel.UiState.initial)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(state = rememberScrollState())
                .padding(bottom = AppTheme.paddings.medium)
        ) {
            Round(playerScores = gameUiState.scores,)

            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxSize()
            ) { Text(text = "+") }
        }

        Text(text = "Round ${gameUiState.roundNumber}")
        gameUiState.scores.forEach {
            PlayerStats(
                playerScore = it,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
private fun PlayerStats(
    playerScore: PlayerScore,
    modifier: Modifier = Modifier
) = Card(
    modifier = modifier.padding(bottom = AppTheme.paddings.medium)
) {
    Column(
        modifier = Modifier
            .padding(vertical = AppTheme.paddings.medium)
            .padding(end = AppTheme.paddings.medium)
    ) {
        Player(name = playerScore.player.name)

        Score(score = playerScore.score.toString())

        Penalties(
            checked = playerScore.hasPenalties,
            points = playerScore.penalties.toString(),
        )

        Total(
            total = playerScore.total.toString(),
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
private fun Player(
    name: String,
    modifier: Modifier = Modifier,
) = Text(
    text = name,
    style = MaterialTheme.typography.titleLarge,
    modifier = modifier
        .padding(start = AppTheme.paddings.medium),
)

@Composable
private fun Score(
    score: String,
    modifier: Modifier = Modifier,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Start,
    modifier = modifier
        .fillMaxWidth()
        .padding(start = AppTheme.paddings.medium)
) {
    Text(text = "Points")
    TextButton(onClick = { /*TODO*/ }) { Text(text = "-") }
    TextButton(onClick = { /*TODO*/ }) { Text(text = "+") }

    Spacer(modifier = Modifier.weight(0.6f))
    Text(text = score)
}

@Composable
private fun Penalties(
    checked: Boolean,
    points: String,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Checkbox(checked = checked, onCheckedChange = {})
        Text(
            text = "Penalties",
            modifier = Modifier.weight(weight = 0.8f)
        )
        Text(text = points)
    }

    if (checked) Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        var oneTileDrawn by remember { mutableStateOf(value = false) }
        var twoTileDrawn by remember { mutableStateOf(value = false) }
        var threeTileDrawn by remember { mutableStateOf(value = false) }
        var threeTileDrawnNonePlaced by remember { mutableStateOf(value = true) }

        PenaltyItem(
            penaltyLabel = "1 tile drawn",
            penalty = -5,
            checked = oneTileDrawn,
            onCheckedChange = { oneTileDrawn = !oneTileDrawn },
        )

        PenaltyItem(
            penaltyLabel = "2 tiles drawn",
            penalty = -10,
            checked = twoTileDrawn,
            onCheckedChange = { twoTileDrawn = it },
        )

        PenaltyItem(
            penaltyLabel = "3 tiles drawn",
            penalty = -15,
            checked = threeTileDrawn,
            onCheckedChange = { threeTileDrawn = it },
        )

        PenaltyItem(
            penaltyLabel = "3 tiles drawn & none placed",
            penalty = -25,
            checked = threeTileDrawnNonePlaced,
            onCheckedChange = { threeTileDrawnNonePlaced = it },
        )
    }
}

@Composable
private fun PenaltyItem(
    penalty: Int,
    penaltyLabel: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) = IconToggleButton(
    checked = checked,
    onCheckedChange = { onCheckedChange(it) },
    colors = IconButtonDefaults.iconToggleButtonColors(
        contentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    ),
    modifier = modifier
        .fillMaxWidth()
        .padding(start = AppTheme.paddings.medium),
) {
    Row {
        Text(
            text = penaltyLabel,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.weight(weight = 0.8f)
        )
        Text(text = penalty.toString())
    }
}

@Composable
private fun Total(
    total: String,
    modifier: Modifier = Modifier,
) = Text(
    text = total,
    fontWeight = FontWeight.Bold,
    style = MaterialTheme.typography.titleLarge,
    modifier = modifier,
)

@Preview
@Composable
private fun ScoreScreenPreview() = AppTheme { GameScreen() }
