package com.tmdb.app.ui.upcoming

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.app.ui.MovieCollection

const val UpcomingMovieCollectionRoute = "movies/upcoming"

fun NavGraphBuilder.upcomingMoviesScreens() = composable(
    route = UpcomingMovieCollectionRoute
) {
    val upcomingMoviesViewModel = hiltViewModel<UpcomingMoviesViewModel>()
    val upcoming = upcomingMoviesViewModel.movies.collectAsLazyPagingItems()

    MovieCollection(movies = upcoming)
}
