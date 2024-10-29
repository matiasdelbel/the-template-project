package com.holidays.app.ui.trip.create

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holidays.app.gateway.TripsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val tripsRepository: TripsRepository
) : ViewModel() {

    private val name by derivedStateOf { nameInputValue.text }
    var nameInputValue by mutableStateOf(value = TextFieldValue(text = ""))
        private set
    val nameInputError by derivedStateOf { name.isBlank() }

    var screenState by mutableStateOf(value = UiState(loading = false))

    fun updateName(input: TextFieldValue) {
        nameInputValue = input
    }

    fun saveTrip() {
        screenState = screenState.copy(loading = true)

        viewModelScope.launch {
            tripsRepository.insertTrip(name = name)

            screenState = screenState.copy(loading = false, finish = true)
        }
    }

    data class UiState(
        val loading: Boolean,
        val finish: Boolean = false,
    )
}
