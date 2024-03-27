package com.triominos.ui.players

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.ui.Form
import com.dbel.design.system.ui.Form.FooterButton

internal const val CreateGameRoute = "triominos/create_game"

internal fun NavGraphBuilder.createGameScreen() =
    composable(route = CreateGameRoute) { CreateGameScreen() }

@Composable
fun CreateGameScreen(
    modifier: Modifier = Modifier,
    createGameViewModel: CreateGameViewModel = viewModel(),
) = Form(
    title = "New game",
    content = {
        val numberOfPlayers = createGameViewModel.gameDraft.numberOfPlayers

        for (position in 0 until numberOfPlayers) {
            val focusRequester = remember { FocusRequester() }

            RemovableTextField(
                label = "Player ${position + 1}",
                text = createGameViewModel.gameDraft.players[position].name,
                onTextChange = { createGameViewModel.editPlayer(position, playerName = it) },
                onRemovedClick = { createGameViewModel.removePlayer(position) },
                modifier = Modifier
                    .padding(bottom = AppTheme.paddings.extraSmall)
                    .focusRequester(focusRequester)
            )

            if (position == numberOfPlayers - 1) LaunchedEffect(Unit) { focusRequester.requestFocus() }
        }

        if (!createGameViewModel.gameDraft.isFull) {
            TextField(
                placeholder = { Text(text = "Player ${numberOfPlayers + 1}") },
                value = "",
                onValueChange = { createGameViewModel.addPlayer(playerName = it) },
                modifier = Modifier.padding(bottom = AppTheme.paddings.extraSmall)
            )
        }
    },
    footer = { FooterButton(text = "Save") { createGameViewModel.createGame() } },
    modifier = modifier,
)

@Composable
fun RemovableTextField(
    label: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    onRemovedClick: () -> Unit = {},
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = label) },
    )

    TextButton(onClick = { onRemovedClick() }) {
        Text(
            text = "-",
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Preview
@Composable
fun CreateGameScreenPreview() = AppTheme {
    CreateGameScreen(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(AppTheme.paddings.medium)
    )
}
