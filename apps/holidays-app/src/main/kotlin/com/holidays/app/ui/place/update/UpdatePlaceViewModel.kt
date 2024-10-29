package com.holidays.app.ui.place.update

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holidays.app.gateway.PlacesRepository
import com.holidays.app.model.Coordinate
import com.holidays.app.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdatePlaceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val placesRepository: PlacesRepository,
) : ViewModel() {

    private val placeId = checkNotNull(value = savedStateHandle.get<Long>("workoutId"))
    private val place = mutableStateOf(value = EmptyRecord)

    private val name by derivedStateOf { nameInputValue.text }
    var nameInputValue by mutableStateOf(value = TextFieldValue(text = ""))
        private set
    val nameInputError by derivedStateOf { name.isBlank() }

    private val longitude by derivedStateOf { longitudeInputFieldState.text.toDoubleOrNull() }
    var longitudeInputFieldState by mutableStateOf(value = TextFieldValue(text = ""))
        private set
    val longitudeFieldError by derivedStateOf { longitude == null }

    private val latitude by derivedStateOf { latitudeInputFieldState.text.toDoubleOrNull() }
    var latitudeInputFieldState by mutableStateOf(value = TextFieldValue(text = ""))
        private set
    val latitudeFieldError by derivedStateOf { latitude == null }

    var screenState by mutableStateOf(value = UiState(loading = false))

    init {
        viewModelScope.launch {
            place.value = placesRepository.findById(placeId = placeId)
            val place = place.value

            nameInputValue = nameInputValue.copy(text = place.name)
            longitudeInputFieldState = longitudeInputFieldState.copy(text = place.coordinate.longitude.toString())
            latitudeInputFieldState = latitudeInputFieldState.copy(text = place.coordinate.longitude.toString())
        }
    }
    fun updateName(input: TextFieldValue) {
        nameInputValue = input
    }

    fun updateLongitude(input: TextFieldValue) {
        longitudeInputFieldState = input.formatAsTimePeriod()
    }

    fun updateLatitude(input: TextFieldValue) {
        latitudeInputFieldState = input.formatAsTimePeriod()
    }

    fun updatePlace() {
        screenState = screenState.copy(loading = true)

        viewModelScope.launch {
            placesRepository.update(place = place.value.copy(
                name = name,
                coordinate = Coordinate(latitude ?: 0.0, longitude ?: 0.0),
            ))

            screenState = screenState.copy(loading = false, finish = true)
        }
    }

    private fun TextFieldValue.formatAsTimePeriod(): TextFieldValue {
        val trunkedInput = text.take(n = 5)
        val text = trunkedInput
            .mapIndexed { index, char -> when {
                index == 2 -> digitCharWithSeparatorOrNull(char)
                else -> digitCharOrNull(char)
            } }
            .filterNotNull()
            .joinToString(separator = "")

        return copy(text = text, selection = TextRange(index = text.length))
    }

    private fun digitCharOrNull(char: Char): String? = if (char.isDigit()) char.toString() else null

    private fun digitCharWithSeparatorOrNull(char: Char): String = if (char.isDigit()) ":$char" else ":"

    data class UiState(
        val loading: Boolean,
        val finish: Boolean = false,
    )
}

private val EmptyRecord: Place get() = Place(
     id = 1L,
     name = "",
     coordinate = Coordinate(0.0, 0.0)
 )