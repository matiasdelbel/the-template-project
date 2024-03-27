package com.triominos.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.app.container.contract.AppScreens
import com.dbel.design.system.theme.AppTheme
import com.triominos.ui.home.HomeRoute
import com.triominos.ui.home.homeScreen
import com.triominos.ui.game.gameScreen
import com.triominos.ui.players.CreateGameRoute
import com.triominos.ui.players.CreateGameScreen
import com.triominos.ui.players.createGameScreen

object TriominosScreens : AppScreens {

    override val startRoute: String = CreateGameRoute

    override val topRoutes: List<String> = listOf(
        HomeRoute,
        CreateGameRoute,
    )

    @Composable
    override fun Home() = CreateGameScreen(
        modifier = Modifier.fillMaxSize().padding(all = AppTheme.paddings.medium)
    )
}

fun NavGraphBuilder.triominosScreens(navController: NavController) {
    homeScreen()
    gameScreen()

    createGameScreen()
}
