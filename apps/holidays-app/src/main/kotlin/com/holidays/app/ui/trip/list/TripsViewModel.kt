package com.holidays.app.ui.trip.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holidays.app.gateway.TripsRepository
import com.holidays.app.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripsViewModel @Inject constructor(
    private val tripsRepository: TripsRepository,
) : ViewModel() {

    val all = tripsRepository
        .trips
        .map { trips -> trips.map { trip -> trip.toUiState() } }

    fun delete(state: PlacesUiState) = viewModelScope.launch {
        tripsRepository.delete(trip = state.trip)
    }

    private fun Trip.toUiState() = PlacesUiState(
        name = name,
        trip = this,
    )

    data class PlacesUiState(
        val name: String,
        val trip: Trip,
    )
}
