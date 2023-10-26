package com.tmdb.app.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems

const val PopularMovieCollectionRoute = "movies/popular"

internal fun NavGraphBuilder.popularMovieCollectionScreen() = composable(
    route = PopularMovieCollectionRoute
) {
    val movieCollectionViewModel: MovieCollectionViewModel = hiltViewModel()
    val populars = movieCollectionViewModel.populars.collectAsLazyPagingItems()

    MovieCollection(populars)
}
