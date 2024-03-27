package com.triominos.ui.game

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.triominos.gateway.GameRepository
import com.triominos.model.PlayerScore
import com.triominos.model.Round
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
) : ViewModel() {

    private val game = flow { emit(value = gameRepository.createGame()) }

    val uiState = game.map { game -> game
        .lastRound()
        .toUiState()
    }

    private fun Round.toUiState() = UiState(
        roundNumber = number,
        scores = scores,
    )

    @Immutable
    data class UiState(
        val roundNumber: Int,
        val scores: List<PlayerScore>,
    ) {

        companion object {
            val initial = UiState(roundNumber = 0, emptyList())
        }
    }
}
