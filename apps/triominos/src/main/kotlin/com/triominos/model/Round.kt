package com.triominos.model

data class Round(
    val number: Int,
    val scores: List<PlayerScore>,
) {

    operator fun get(player: Player): PlayerScore = scores.first { it.player == player }
}