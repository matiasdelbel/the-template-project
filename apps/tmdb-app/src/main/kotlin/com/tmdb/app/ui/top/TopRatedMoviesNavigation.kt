package com.tmdb.app.ui.top

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.app.ui.MovieCollection

const val TopRatedMovieCollectionRoute = "movies/top_rated"

fun NavGraphBuilder.topRatedMoviesScreens() = composable(
    route = TopRatedMovieCollectionRoute
) {
    val topRatedViewModel = hiltViewModel<TopRatedViewModel>()
    val topRated = topRatedViewModel.movies.collectAsLazyPagingItems()

    MovieCollection(movies = topRated)
}
