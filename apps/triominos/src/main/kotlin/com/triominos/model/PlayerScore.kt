package com.triominos.model

data class PlayerScore(
    val player: Player,
    val score: Int,
    val penalties: Int,
) {

    val total: Int = score - penalties

    val hasPenalties: Boolean = penalties > 0
}