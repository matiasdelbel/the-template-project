package com.rijks.app.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijks.app.data.ArtCollectionRepository
import com.rijks.app.model.ArtObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtObjectViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val artCollectionRepository: ArtCollectionRepository,
) : ViewModel() {

    private val artObjectArgs: ArtObjectArgs = ArtObjectArgs(savedStateHandle)

    private val _uiState = MutableStateFlow(value = artObjectArgs.toUiState())
    val uiState: StateFlow<UiState> get() = _uiState

    private val handler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = _uiState.value.copy(showFacts = false, showError = true)
    }

    init {
        obtainArtObject()
    }

    fun obtainArtObject() = viewModelScope.launch(handler) {
        val artObject = artCollectionRepository.obtainArtObject(artObjectArgs.artObjectNumber)
        _uiState.value = artObject.toUiState()
    }

    private fun ArtObjectArgs.toUiState() = UiState(
        title = artObjectTitle,
        imageUrl = artObjectImageUrl,
        showFacts = false,
        showError = false
    )

    private fun ArtObject.toUiState(previous: UiState = _uiState.value) = previous.copy(
        subtitle = subtitle,
        description = description,
        date = date,
        materials = materials.joinToString(separator = ", "),
        principalMakers = principalMakers.joinToString(separator = ", "),
        showFacts = true,
        showError = false,
    )

    data class UiState(
        val imageUrl: String,
        val title: String,
        val subtitle: String? = null,
        val description: String? = null,
        val date: String? = null,
        val materials: String? = null,
        val principalMakers: String? = null,
        val showFacts: Boolean = false,
        val showError: Boolean = false,
    )
}
