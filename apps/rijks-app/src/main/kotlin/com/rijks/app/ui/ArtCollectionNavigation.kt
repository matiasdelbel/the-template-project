package com.rijks.app.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.rijks.app.model.ArtObjectOverview

fun NavGraphBuilder.artCollectionScreen(
    onArtObjectSelected: (artObject: ArtObjectOverview) -> Unit
) = composable(
    route = ArtCollectionRoute
) {
    val artCollectionViewModel: ArtCollectionViewModel = hiltViewModel()
    val artObjects = artCollectionViewModel.artObjects.collectAsLazyPagingItems()

    ArtCollection(artObjects) { artObject -> onArtObjectSelected(artObject) }
}

internal const val ArtCollectionRoute = "art_collection"
