package com.tmdb.app.ui.listing

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.dbel.design.system.component.PagingGrid
import com.dbel.design.system.theme.AppTheme
import com.tmdb.app.R
import com.tmdb.app.model.Movie

@Composable
fun Movies(
    nowPlaying: LazyPagingItems<Movie>,
    populars: LazyPagingItems<Movie>,
    topRated: LazyPagingItems<Movie>,
    upcoming: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier
) {
    val selected = remember { mutableIntStateOf(value = 0) }

    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = AppTheme.spacers.sm),
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(state = rememberScrollState())
        ) {
            FilterChip(
                selected = selected.intValue == NowPlayingMoviesIndex,
                onClick = { selected.intValue = NowPlayingMoviesIndex },
                label = { Text(text = "Now playing") }
            )

            FilterChip(
                selected = selected.intValue == PopularMoviesIndex,
                onClick = { selected.intValue = PopularMoviesIndex },
                label = { Text(text = "Popular") },
            )

            FilterChip(
                selected = selected.intValue == TopRatedMoviesIndex,
                onClick = { selected.intValue = TopRatedMoviesIndex },
                label = { Text(text = "Top rated") }
            )

            FilterChip(
                selected = selected.intValue == UpcomingMoviesIndex,
                onClick = { selected.intValue = UpcomingMoviesIndex },
                label = { Text(text = "Upcoming") }
            )
        }

        when (selected.intValue) {
            NowPlayingMoviesIndex -> MoviePagingGrid(movies = nowPlaying, modifier)
            PopularMoviesIndex -> MoviePagingGrid(movies = populars, modifier)
            TopRatedMoviesIndex -> MoviePagingGrid(movies = topRated, modifier)
            UpcomingMoviesIndex -> MoviePagingGrid(movies = upcoming, modifier)
        }
    }
}

@Composable
private fun MoviePagingGrid(
    movies: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier,
) {
    val placeholderPosters = placeholderPosters

    PagingGrid(
        items = movies,
        modifier = modifier,
        loadStateAppend = { items(placeholderPosters) { MoviePlaceholder(painter = it) } },
        loadStateAppendError = {
            item(span = { GridItemSpan(currentLineSpan = 3) }) {
                Error(
                    label = stringResource(R.string.seems_that_we_are_having_some_issues_loading_the_collection),
                    actionLabel = stringResource(R.string.load_more),
                    onAction = { it.retry() },
                )
            }
        },
        loadStateRefresh = { items(placeholderPosters) { MoviePlaceholder(painter = it) } },
        loadStateRefreshError = {
            items(placeholderPosters) { MoviePlaceholder(painter = it) }

            item(span = { GridItemSpan(currentLineSpan = 3) }) {
                Error(
                    label = stringResource(R.string.seems_that_we_are_having_some_issues_loading_the_collection),
                    actionLabel = stringResource(R.string.try_again),
                    onAction = { it.retry() },
                )
            }
        },
        transformation = { movie ->
            Movie(
                imageUrl = movie.posterPath,
                contentDescription = movie.title,
            )
        }
    )
}

@Composable
private fun Movie(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) = AsyncImage(
    modifier = modifier.clip(RoundedCornerShape(size = AppTheme.spacers.xs)),
    model = imageUrl,
    contentScale = ContentScale.FillWidth,
    contentDescription = contentDescription
)

@Composable
private fun MoviePlaceholder(
    painter: Painter,
    modifier: Modifier = Modifier,
) = Image(
    painter = painter,
    contentDescription = stringResource(R.string.movie_placeholder),
    alpha = 0.28f,
    modifier = modifier.clip(RoundedCornerShape(size = AppTheme.spacers.xs))
)

@Composable
fun Error(
    label: String,
    actionLabel: String,
    modifier: Modifier = Modifier,
    onAction: () -> Unit = {},
) = Card(modifier = modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(all = AppTheme.spacers.md)
    ) {
        Text(text = label, textAlign = TextAlign.Center)
        TextButton(onClick = { onAction() }) { Text(text = actionLabel) }
    }
}

private val placeholderPosters @Composable get () = listOf(
    painterResource(id = R.drawable.holder_batman),
    painterResource(id = R.drawable.holder_godfather),
    painterResource(id = R.drawable.holder_godfather_ii),
    painterResource(id = R.drawable.holder_robot),
    painterResource(id = R.drawable.holder_schindler),
    painterResource(id = R.drawable.holder_shawshank),
)

private const val NowPlayingMoviesIndex = 0
private const val PopularMoviesIndex = 1
private const val TopRatedMoviesIndex = 2
private const val UpcomingMoviesIndex = 3

@Preview
@Composable
private fun MoviePlaceholderPreview() = MoviePlaceholder(painter = placeholderPosters[1])
