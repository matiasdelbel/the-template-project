package com.holidays.app.ui.trip.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holidays.app.gateway.TripsRepository
import com.holidays.app.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tripsRepository: TripsRepository,
) : ViewModel() {

    private val tripId = checkNotNull(value = savedStateHandle.get<Long>("tripId"))
    var screenState by mutableStateOf(value = UiState(loading = false))

    init { searchTrip() }

    fun delete(trip: Trip) = viewModelScope.launch {
        tripsRepository.delete(trip = trip)
    }

    private fun searchTrip() {
        screenState = screenState.copy(loading = true)

        viewModelScope.launch {
            val trip = tripsRepository.findById(tripId)
            screenState = screenState.copy(
                name = trip.name,
                links = trip.links.map { link -> link.url },
                loading = false
            )
        }
    }

    data class UiState(
        val name: String = "",
        val links: List<String> = emptyList(),
        val loading: Boolean = false,
    )
}
