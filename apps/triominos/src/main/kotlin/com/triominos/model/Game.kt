package com.triominos.model

data class Game(
    private val players: List<Player>,
    private val rounds: List<Round>,
) {

    fun lastRound(): Round = rounds.maxBy { it.number }
}

data class GameDraft(
    val players: List<Player> = emptyList(),
) {

    val numberOfPlayers: Int get() = players.size

    val isFull: Boolean get() = numberOfPlayers >= 6
}
