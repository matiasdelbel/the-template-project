package com.tmdb.app.ui.listing

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.dbel.design.system.component.SearchPane
import com.dbel.design.system.theme.AppTheme
import com.tmdb.app.model.Movie
import com.tmdb.app.ui.components.MovieCollectionPicker
import com.tmdb.app.ui.components.MoviesGrid
import com.tmdb.app.ui.components.NowPlayingMoviesIndex
import com.tmdb.app.ui.components.PopularMoviesIndex
import com.tmdb.app.ui.components.TopRatedMoviesIndex
import com.tmdb.app.ui.components.UpcomingMoviesIndex

@Composable
fun MoviesPane(
    nowPlaying: LazyPagingItems<Movie>,
    populars: LazyPagingItems<Movie>,
    topRated: LazyPagingItems<Movie>,
    upcoming: LazyPagingItems<Movie>,
    query: String,
    results: LazyPagingItems<Movie>,
    onQueryChange: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val selected = remember { mutableIntStateOf(value = 0) }

    Box(
        modifier = modifier.fillMaxSize().semantics { isTraversalGroup = true }
    ) {
        SearchPane(
            query = query,
            content = { MoviesGrid(movies = results) },
            onQueryChange = onQueryChange,
            modifier = Modifier.align(Alignment.TopCenter).semantics { traversalIndex = 0f },
        )

        MoviesGrid(
            header = {
                MovieCollectionPicker(
                    selectedIndex = selected.intValue,
                    modifier = Modifier.focusGroup(),
                    onSelected = { selected.intValue = it }
                )
            },
            movies = when (selected.intValue) {
                NowPlayingMoviesIndex -> nowPlaying
                PopularMoviesIndex -> populars
                TopRatedMoviesIndex -> topRated
                UpcomingMoviesIndex -> upcoming
                else -> error("")
            },
            contentPadding = PaddingValues(
                start = AppTheme.spacers.sm,
                end = AppTheme.spacers.sm,
                bottom = AppTheme.spacers.sm,
                top = 72.dp
            ),
            modifier = Modifier.semantics { traversalIndex = 1f },
        )
    }
}
