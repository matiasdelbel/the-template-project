package com.triominos.gateway

import com.triominos.model.Game
import com.triominos.model.GameDraft
import com.triominos.model.Player
import com.triominos.model.PlayerScore
import com.triominos.model.Round
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor() {

    suspend fun create(from: GameDraft): Game = Game(
        players = from.players,
        rounds = emptyList()
    )

    // TODO remove
    fun createGame(): Game {
        val players = listOf(
            Player(name = "Matias"),
            Player(name = "Cande")
        )

        return Game(
            players = players,
            rounds = listOf(
                Round(
                    number = 1,
                    scores = listOf(
                        PlayerScore(players[0], score = 30, penalties = 0),
                        PlayerScore(players[1], score = 20, penalties = -5),
                    )
                ),
                Round(
                    number = 2,
                    scores = listOf(
                        PlayerScore(players[0], score = 20, penalties = 0),
                        PlayerScore(players[1], score = 50, penalties = -10),
                    )
                )
            ),
        )
    }
}
