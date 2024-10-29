package com.holidays.app.ui.trip.update

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holidays.app.gateway.TripsRepository
import com.holidays.app.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateTripViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tripsRepository: TripsRepository,
) : ViewModel() {

    private val tripId = checkNotNull(value = savedStateHandle.get<Long>("tripId"))
    private val trip = mutableStateOf(value = EmptyRecord)

    private val name by derivedStateOf { nameInputValue.text }
    var nameInputValue by mutableStateOf(value = TextFieldValue(text = ""))
        private set
    val nameInputError by derivedStateOf { name.isBlank() }

    var screenState by mutableStateOf(value = UiState(loading = false))

    init {
        viewModelScope.launch {
            trip.value = tripsRepository.findById(tripId = tripId)
            val place = trip.value

            nameInputValue = nameInputValue.copy(text = place.name)
        }
    }

    fun updateName(input: TextFieldValue) {
        nameInputValue = input
    }

    fun updateTrip() {
        screenState = screenState.copy(loading = true)

        viewModelScope.launch {
            tripsRepository.update(trip = trip.value.copy(name = name))

            screenState = screenState.copy(loading = false, finish = true)
        }
    }

    data class UiState(
        val loading: Boolean,
        val finish: Boolean = false,
    )
}

private val EmptyRecord
    get() = Trip(
        id = 1L,
        name = "",
    )