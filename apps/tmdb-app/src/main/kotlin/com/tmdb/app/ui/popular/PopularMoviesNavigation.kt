package com.tmdb.app.ui.popular

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.app.ui.MovieCollection

const val PopularMovieCollectionRoute = "movies/popular"

internal fun NavGraphBuilder.popularMoviesScreens() = composable(
    route = PopularMovieCollectionRoute
) {
    val popularMoviesViewModel = hiltViewModel<PopularMoviesViewModel>()
    val populars = popularMoviesViewModel.movies.collectAsLazyPagingItems()

    MovieCollection(movies = populars)
}
