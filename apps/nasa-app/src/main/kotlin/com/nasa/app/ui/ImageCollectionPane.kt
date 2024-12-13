package com.nasa.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.dbel.design.system.component.SearchPane
import com.dbel.design.system.theme.AppTheme
import com.nasa.app.R
import com.nasa.app.model.Image

@Composable
fun ImageCollectionPane(
    artObjects: LazyPagingItems<Image>,
    modifier: Modifier = Modifier,
    query: String,
    results: LazyPagingItems<Image>,
    onQueryChange: (query: String) -> Unit,
    onArtObjectSelected: (image: Image) -> Unit = { },
) {
    Box(
        modifier = modifier.fillMaxSize().semantics { isTraversalGroup = true }
    ) {
        SearchPane(
            query = query,
            onQueryChange = onQueryChange,
            placeholder = "Search a image",
            results = { Images(results, onImageSelected = onArtObjectSelected) },
            modifier = Modifier.align(Alignment.TopCenter).semantics { traversalIndex = 0f },
        )

        Images(artObjects, onImageSelected = onArtObjectSelected)
    }
}

@Composable
private fun Images(
    images: LazyPagingItems<Image>,
    modifier: Modifier = Modifier,
    onImageSelected: (image: Image) -> Unit = { },
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = AppTheme.spacers.sm,
            end = AppTheme.spacers.sm,
            bottom = AppTheme.spacers.sm,
            top = 72.dp
        ),
    ) {
        items(images.itemCount) { index ->
            val image = images[index]!!

            Image(
                imageUrl = image.links.first().href,
                contentDescription = image.data.first().title,
                imageLabel =  image.data.first().title,
                modifier = modifier.clickable { onImageSelected(image) }
            )
        }

        refreshLoadState(images)
    }
}

@Composable
fun Image(
    imageUrl: String,
    contentDescription: String,
    imageLabel: String,
    modifier: Modifier = Modifier,
) = Card(
    modifier = modifier
        .padding(horizontal = 16.dp)
        .padding(vertical = 8.dp)
) {
    Column {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
        )

        Text(
            text = imageLabel,
            style = AppTheme.typography.titleSmall,
            modifier = modifier.padding(all = 16.dp)
        )
    }
}

fun LazyListScope.refreshLoadState(items: LazyPagingItems<Image>) = items.apply {
    when {
        loadState.refresh is LoadState.Loading -> {
            item { Artist(name = stringResource(id = R.string.loading_art_collection)) }
            item { ArtObjectPlaceholder() }
            item { ArtObjectPlaceholder() }
        }

        loadState.append is LoadState.Loading -> {
            item { ArtObjectPlaceholder() }
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
            item { ArtObjectPlaceholder(loading = false) }
            item { ArtObjectPlaceholder(loading = false) }
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
    style = AppTheme.typography.titleLarge,
    text = name,
    fontWeight = FontWeight.Bold,
    modifier = modifier
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp)
)

@Composable
private fun ArtObjectPlaceholder(
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
            style = AppTheme.typography.titleSmall,
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
private fun ArtObjectPlaceholderPreview() = ArtObjectPlaceholder()

@Preview
@Composable
private fun ErrorPreview() = Error()
