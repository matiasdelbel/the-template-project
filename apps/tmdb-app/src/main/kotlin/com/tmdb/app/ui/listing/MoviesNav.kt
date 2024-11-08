package com.tmdb.app.ui.listing

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems

fun NavGraphBuilder.moviesPane() = composable(route = MoviesRoute) {
    val moviesViewModel = hiltViewModel<MoviesViewModel>()

    Movies(
        nowPlaying = moviesViewModel.nowPlaying.collectAsLazyPagingItems(),
        populars = moviesViewModel.populars.collectAsLazyPagingItems(),
        topRated = moviesViewModel.topRated.collectAsLazyPagingItems(),
        upcoming = moviesViewModel.upcoming.collectAsLazyPagingItems(),
    )
}

const val MoviesRoute = "movies"