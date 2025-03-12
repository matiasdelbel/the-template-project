package com.rijks.app.ui

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.rijks.app.model.ArtObjectOverview
import kotlinx.coroutines.flow.map

fun NavGraphBuilder.artCollectionPane(
    onArtObjectSelected: (artObject: ArtObjectOverview) -> Unit
) = composable(
    route = ArtCollectionRoute
) {
    val artCollectionViewModel: ArtCollectionViewModel = hiltViewModel()

    val artObjects = artCollectionViewModel.artObjects.collectAsLazyPagingItems()

    ArtCollectionPane(
        artObjects = artObjects,
        searchState = artCollectionViewModel.state,
        onQueryChange = { artCollectionViewModel.search(query = it) },
        onArtObjectSelected = { artObject -> onArtObjectSelected(artObject) }
    )
}

internal const val ArtCollectionRoute = "art_collection"
