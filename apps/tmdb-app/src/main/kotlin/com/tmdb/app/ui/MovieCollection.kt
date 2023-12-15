package com.tmdb.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.dbel.design.system.theme.AppTheme
import com.tmdb.app.model.Movie
import com.tmdb.app.R

@Composable
fun MovieCollection(
    artObjects: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier.padding(horizontal = AppTheme.paddings.small),
) = LazyColumn(modifier = modifier) {
    items(artObjects.itemCount) { index ->
        val artObject = artObjects[index]!!

        Movie(
            imageUrl = artObject.posterPath,
            contentDescription = artObject.title,
        )
    }

    refreshLoadState(artObjects)
}

@Composable
fun Movie(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) = Card(
    modifier = modifier.padding(vertical = AppTheme.paddings.small)
) {
    Column {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = imageUrl,
            contentScale = ContentScale.FillWidth,
            contentDescription = contentDescription,
        )
    }
}

fun LazyListScope.refreshLoadState(items: LazyPagingItems<Movie>) = items.apply {
    when {
        loadState.refresh is LoadState.Loading -> {
            item { Artist(name = stringResource(id = R.string.loading_art_collection)) }
            item { MoviePlaceholder() }
            item { MoviePlaceholder() }
        }

        loadState.append is LoadState.Loading -> {
            item { MoviePlaceholder() }
        }

        loadState.refresh is LoadState.Error -> {
            item { Artist(name = stringResource(id = R.string.ups_something_went_wrong)) }
            item {
                Error(
                    label = stringResource(R.string.seems_that_we_are_having_some_issues_loading_the_collection),
                    actionLabel = stringResource(R.string.try_again),
                    onAction = ::retry,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
            item { MoviePlaceholder(loading = false) }
            item { MoviePlaceholder(loading = false) }
        }

        loadState.append is LoadState.Error -> {
            item {
                Error(
                    label = stringResource(R.string.seems_that_we_are_having_some_issues_loading_the_collection),
                    actionLabel = stringResource(R.string.load_more),
                    onAction = ::retry,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
        }
    }
}

@Composable
fun Artist(name: String, modifier: Modifier = Modifier) = Text(
    style = MaterialTheme.typography.titleLarge,
    text = name,
    fontWeight = FontWeight.Bold,
    modifier = modifier
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp)
)

@Composable
private fun MoviePlaceholder(
    modifier: Modifier = Modifier,
    loading: Boolean = true
) = Card(
    modifier = modifier
        .padding(horizontal = 16.dp)
        .padding(vertical = 8.dp),
) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.art_object_holder),
            contentDescription = stringResource(R.string.art_object_image_placeholder),
            alpha = 0.6f,
            modifier = modifier.blur(6.dp)
        )

        if (loading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

        Text(
            text = stringResource(R.string.de_nachtwacht_rembrandt),
            style = MaterialTheme.typography.titleSmall,
            modifier = modifier
                .padding(all = 16.dp)
                .blur(6.dp)
        )
    }
}

@Composable
fun Error(
    label: String,
    actionLabel: String,
    modifier: Modifier = Modifier,
    onAction: () -> Unit = {},
) = Card(modifier = modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(all = 16.dp)
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextButton(onClick = { onAction() }) { Text(text = actionLabel) }
    }
}

@Preview
@Composable
private fun MoviePlaceholderPreview() = MoviePlaceholder()

@Preview
@Composable
private fun ErrorPreview() = Error()
