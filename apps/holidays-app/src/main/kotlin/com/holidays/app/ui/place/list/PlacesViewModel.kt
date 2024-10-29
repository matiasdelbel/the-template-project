package com.holidays.app.ui.place.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holidays.app.gateway.PlacesRepository
import com.holidays.app.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
) : ViewModel() {

    val all = placesRepository
        .places
        .map { places -> places.map { place -> place.toUiState() } }

    fun delete(state: PlacesUiState) = viewModelScope.launch {
        placesRepository.delete(place = state.place)
    }

    private fun Place.toUiState() = PlacesUiState(
        name = name,
        latitude = "${coordinate.latitude}",
        longitude = "${coordinate.longitude}",
        place = this,
    )

    data class PlacesUiState(
        val name: String,
        val latitude: String,
        val longitude: String,
        val place: Place,
    )
}
