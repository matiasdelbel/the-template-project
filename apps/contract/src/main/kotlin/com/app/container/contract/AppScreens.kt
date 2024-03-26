package com.app.container.contract

import androidx.compose.runtime.Composable

interface AppScreens {

    val startRoute: String
    val topRoutes: List<String>

    @Composable
    fun Home()
}
