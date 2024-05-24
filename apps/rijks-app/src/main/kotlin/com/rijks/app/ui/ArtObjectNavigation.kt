package com.rijks.app.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rijks.app.model.ArtObjectOverview

internal class ArtObjectArgs(
    val artObjectNumber: String,
    val artObjectTitle: String,
    val artObjectImageUrl: String,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        artObjectNumber = checkNotNull(savedStateHandle[ArtObjectNumberArg]) as String,
        artObjectTitle = checkNotNull(savedStateHandle[ArtObjectTitleArg]) as String,
        artObjectImageUrl = checkNotNull(savedStateHandle[ArtObjectImageUrlArg]) as String,
    )
}

fun NavGraphBuilder.artObjectScreen() = composable(
    route = "$ArtObjectRoute/{$ArtObjectNumberArg}?$ArtObjectTitleArg={$ArtObjectTitleArg}&$ArtObjectImageUrlArg={$ArtObjectImageUrlArg}",
    arguments = listOf(
        navArgument(name = ArtObjectNumberArg) {
            type = NavType.StringType
        },
        navArgument(name = ArtObjectTitleArg) {
            type = NavType.StringType
            nullable = true
        },
        navArgument(name = ArtObjectImageUrlArg) {
            type = NavType.StringType
            nullable = true
        }
    )
) {
    val artObjectViewModel: ArtObjectViewModel = hiltViewModel()
    val uiState by artObjectViewModel.uiState.collectAsState()

    ArtObject(uiState) { artObjectViewModel.obtainArtObject() }
}

fun NavController.navigateToArtObject(artObject: ArtObjectOverview) {
    navigate(route = "$ArtObjectRoute/${artObject.objectNumber}?$ArtObjectTitleArg=${artObject.title}&$ArtObjectImageUrlArg=${artObject.imageUrl}")
}

const val ArtObjectRoute = "art_object"

private const val ArtObjectNumberArg = "art_object_number"
private const val ArtObjectTitleArg = "art_object_title"
private const val ArtObjectImageUrlArg = "art_object_image_url"
