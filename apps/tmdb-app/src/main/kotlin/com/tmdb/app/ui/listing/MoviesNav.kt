package com.tmdb.app.ui.listing

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems

fun NavGraphBuilder.moviesPane() = composable(route = MoviesRoute) {
    val moviesViewModel = hiltViewModel<MoviesViewModel>()
    val searchViewModel = hiltViewModel<SearchMoviesViewModel>()

    MoviesPane(
        nowPlaying = moviesViewModel.nowPlaying.collectAsLazyPagingItems(),
        populars = moviesViewModel.populars.collectAsLazyPagingItems(),
        topRated = moviesViewModel.topRated.collectAsLazyPagingItems(),
        upcoming = moviesViewModel.upcoming.collectAsLazyPagingItems(),
        query = searchViewModel.query.collectAsState().value,
        results = searchViewModel.results.collectAsLazyPagingItems(),
        onQueryChange = { searchViewModel.search(query = it) }
    )
}

const val MoviesRoute = "movies"