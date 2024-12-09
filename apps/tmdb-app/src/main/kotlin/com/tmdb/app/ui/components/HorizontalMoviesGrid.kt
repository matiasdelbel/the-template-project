package com.tmdb.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import com.dbel.design.system.component.PaginatedHorizontalGrid
import com.dbel.design.system.theme.AppTheme
import com.tmdb.app.R
import com.tmdb.app.model.Movie

@Composable
fun HorizontalMoviesGrid(
    movies: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(all = AppTheme.spacers.sm),
    header: @Composable () -> Unit = {},
) {
    Column {
        header()

        val placeholderPosters = placeholderPosters.shuffled()

        PaginatedHorizontalGrid(
            items = movies,
            modifier = modifier,
            contentPadding = contentPadding,
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
}
