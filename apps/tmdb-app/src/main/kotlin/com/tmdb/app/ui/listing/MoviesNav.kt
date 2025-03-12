package com.tmdb.app.ui.listing

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems

fun NavGraphBuilder.moviesPane() = composable(route = MoviesRoute) {
    val moviesViewModel = hiltViewModel<MoviesViewModel>()

    MoviesPane(
        nowPlaying = moviesViewModel.nowPlaying.collectAsLazyPagingItems(),
        populars = moviesViewModel.populars.collectAsLazyPagingItems(),
        topRated = moviesViewModel.topRated.collectAsLazyPagingItems(),
        upcoming = moviesViewModel.upcoming.collectAsLazyPagingItems(),
        searchState = moviesViewModel.state,
        onQueryChange = { moviesViewModel.search(query = it) }
    )
}

const val MoviesRoute = "movies"