package com.rijks.app.ui

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.dbel.design.system.component.SearchPane
import com.dbel.design.system.theme.AppTheme
import com.rijks.app.model.ArtObjectOverview
import com.rijks.app.R

@Composable
fun ArtCollection(
    artObjects: LazyPagingItems<ArtObjectOverview>,
    modifier: Modifier = Modifier,
    query: String,
    results: LazyPagingItems<ArtObjectOverview>,
    onQueryChange: (query: String) -> Unit,
    onArtObjectSelected: (artObject: ArtObjectOverview) -> Unit = { },
) {
    Box(
        modifier = modifier.fillMaxSize().semantics { isTraversalGroup = true }
    ) {
        SearchPane(
            query = query,
            content = { ArtCollection(results, onArtObjectSelected = onArtObjectSelected) },
            onQueryChange = onQueryChange,
            modifier = Modifier.align(Alignment.TopCenter).semantics { traversalIndex = 0f },
        )

        ArtCollection(artObjects, onArtObjectSelected = onArtObjectSelected)
    }
}

@Composable
private fun ArtCollection(
    artObjects: LazyPagingItems<ArtObjectOverview>,
    modifier: Modifier = Modifier,
    onArtObjectSelected: (artObject: ArtObjectOverview) -> Unit = { },
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = AppTheme.spacers.sm,
            end = AppTheme.spacers.sm,
            bottom = AppTheme.spacers.sm,
            top = 72.dp
        ),
    ) {
        items(artObjects.itemCount) { index ->
            val artObject = artObjects[index]!!
            val previousArtObject = if (index == 0) null else artObjects[index - 1]!!
            if (artObject.principalOrFirstMaker != previousArtObject?.principalOrFirstMaker)
                Artist(name = artObject.principalOrFirstMaker)

            ArtObjectOverview(
                imageUrl = artObject.imageUrl,
                contentDescription = artObject.title,
                imageLabel = artObject.title,
                modifier = modifier.clickable { onArtObjectSelected(artObject) }
            )
        }

        refreshLoadState(artObjects)
    }
}

fun LazyListScope.refreshLoadState(items: LazyPagingItems<ArtObjectOverview>) = items.apply {
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

@Preview
@Composable
private fun ArtObjectPlaceholderPreview() = ArtObjectPlaceholder()

@Preview
@Composable
private fun ErrorPreview() = Error()
