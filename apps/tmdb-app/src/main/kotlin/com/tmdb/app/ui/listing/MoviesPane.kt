package com.tmdb.app.ui.listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dbel.design.system.pane.SearchPane
import com.dbel.design.system.pane.SearchState
import com.dbel.design.system.theme.AppTheme
import com.tmdb.app.model.Movie
import com.tmdb.app.ui.components.HorizontalMoviesGrid
import com.tmdb.app.ui.components.MovieCollectionTitle
import com.tmdb.app.ui.components.VerticalMoviesGrid
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

@Composable
fun MoviesPane(
    nowPlaying: LazyPagingItems<Movie>,
    populars: LazyPagingItems<Movie>,
    topRated: LazyPagingItems<Movie>,
    upcoming: LazyPagingItems<Movie>,
    searchState: StateFlow<SearchState<Movie>>,
    onQueryChange: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val appBarMaxHeightPx = with(density) { AppBarHeight.roundToPx() }
    val connection = remember(appBarMaxHeightPx) {
        CollapsingAppBarNestedScrollConnection(appBarMaxHeightPx)
    }
    val spaceHeight by remember(density) {
        derivedStateOf { with(density) { (appBarMaxHeightPx + connection.appBarOffset).toDp() } }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(connection)
    ) {
        val searchStateState = searchState.collectAsState()
        val results = searchState.map { it.results }.collectAsLazyPagingItems()

        SearchPane(
            query = searchStateState.value.query,
            onQueryChange = onQueryChange,
            placeholder = "Search movies",
            results = { VerticalMoviesGrid(movies = results) },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset { IntOffset(0, connection.appBarOffset) }
        )

        Column(
            modifier.verticalScroll(state = rememberScrollState())
        ) {
            Spacer(
                Modifier
                    .padding(top = AppTheme.spacers.md)
                    .height(spaceHeight)
            )

            HorizontalMoviesGrid(
                movies = upcoming,
                modifier = Modifier.height(height = MoviePosterProminentHeight),
                header = {
                    MovieCollectionTitle(
                        text = "Upcoming",
                        modifier = Modifier
                            .padding(horizontal = AppTheme.spacers.lg)
                    )
                }
            )

            HorizontalMoviesGrid(
                movies = topRated,
                modifier = Modifier.height(height = MoviePosterDefaultHeight),
                header = {
                    MovieCollectionTitle(
                        text = "Top rated",
                        modifier = Modifier
                            .padding(top = AppTheme.spacers.xs)
                            .padding(horizontal = AppTheme.spacers.lg)
                    )
                },
            )

            HorizontalMoviesGrid(
                movies = nowPlaying,
                modifier = Modifier.height(height = MoviePosterDefaultHeight),
                header = {
                    MovieCollectionTitle(
                        text = "Now playing",
                        modifier = Modifier
                            .padding(top = AppTheme.spacers.xs)
                            .padding(horizontal = AppTheme.spacers.lg)
                    )
                }
            )

            HorizontalMoviesGrid(
                movies = populars,
                modifier = Modifier.height(height = MoviePosterDefaultHeight),
                header = {
                    MovieCollectionTitle(
                        text = "Populars",
                        modifier = Modifier
                            .padding(top = AppTheme.spacers.xs)
                            .padding(horizontal = AppTheme.spacers.lg)
                    )
                },
            )
        }
    }
}

private class CollapsingAppBarNestedScrollConnection(
    val appBarMaxHeight: Int
) : NestedScrollConnection {

    var appBarOffset: Int by mutableIntStateOf(0)
        private set

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.y.toInt()
        val newOffset = appBarOffset + delta
        val previousOffset = appBarOffset

        appBarOffset = newOffset.coerceIn(-appBarMaxHeight, 0)
        val consumed = appBarOffset - previousOffset

        return Offset(0f, consumed.toFloat())
    }
}

private val AppBarHeight = 64.dp // it should be 56.dp

private val MoviePosterProminentHeight = 380.dp
private val MoviePosterDefaultHeight = 240.dp
