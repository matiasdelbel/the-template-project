package com.triominos.ui.players

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triominos.gateway.GameRepository
import com.triominos.model.GameDraft
import com.triominos.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor(
) : ViewModel() {

    // TODO navegar a la pantalla siguiente

    private val gameRepository: GameRepository = GameRepository() // TODO

    var gameDraft by mutableStateOf(value = GameDraft())
        private set

    fun addPlayer(playerName: String) {
        if (playerName.isEmpty()) return

        val players = gameDraft
            .players
            .toMutableList()
            .apply { add(Player(name = playerName)) }

        gameDraft = gameDraft.copy(players = players)
    }

    fun editPlayer(position: Int, playerName: String) {
        val players = gameDraft
            .players
            .toMutableList()
            .apply { set(position, Player(name = playerName)) }

        gameDraft = gameDraft.copy(players = players)
    }

    fun removePlayer(position: Int) {
        val players = gameDraft
            .players
            .toMutableList()
            .apply { removeAt(position) }

        gameDraft = gameDraft.copy(players = players)
    }

    fun createGame() = viewModelScope.launch {
        gameRepository.create(from = gameDraft)
    }
}