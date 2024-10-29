package com.holidays.app.ui.place.create

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holidays.app.gateway.PlacesRepository
import com.holidays.app.model.Coordinate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePlaceViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
) : ViewModel() {

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

    fun updateName(input: TextFieldValue) {
        nameInputValue = input
    }

    fun updateLongitude(input: TextFieldValue) {
        longitudeInputFieldState = input.formatAsTimePeriod()
    }

    fun updateLatitude(input: TextFieldValue) {
        latitudeInputFieldState = input.formatAsTimePeriod()
    }

    fun savePlace() {
        screenState = screenState.copy(loading = true)

        viewModelScope.launch {
            placesRepository.insertPlace(
                name = name,
                coordinate = Coordinate(
                    latitude = latitude ?: 0.0,
                    longitude = longitude ?: 0.0
                ),
            )

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
