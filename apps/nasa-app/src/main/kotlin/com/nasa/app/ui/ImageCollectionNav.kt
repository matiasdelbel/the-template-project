package com.nasa.app.ui

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.nasa.app.model.Image

fun NavGraphBuilder.imageCollectionPane(onSelected: (image: Image) -> Unit) = composable(route = ImageCollectionRoute) {
    val imageCollectionViewModel = hiltViewModel<SearchImageCollectionViewModel>()
    val searchViewModel = hiltViewModel<SearchImageCollectionViewModel>()

    val earthImages = imageCollectionViewModel
        .doSearch(query = "earth")
        .collectAsLazyPagingItems()

    ImageCollectionPane(
        artObjects = earthImages,
        query = searchViewModel.query.collectAsState().value,
        results = searchViewModel.results.collectAsLazyPagingItems(),
        onQueryChange = { searchViewModel.search(query = it) },
        onArtObjectSelected = { image -> onSelected(image) }
    )
}

internal const val ImageCollectionRoute = "image_collection"
