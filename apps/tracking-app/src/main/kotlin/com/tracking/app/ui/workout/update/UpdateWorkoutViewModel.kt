package com.tracking.app.ui.workout.update

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tracking.app.gateway.WorkoutRepository
import com.tracking.app.model.TimePeriod
import com.tracking.app.model.Workout
import com.tracking.app.ui.workout.list.dateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateWorkoutViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    private val workoutId = checkNotNull(value = savedStateHandle.get<Long>("workoutId"))
    private val workout = mutableStateOf(value = Workout.Running.EmptyRecord)

    private val date by derivedStateOf { workout.value.date }
    val dateString by derivedStateOf<String> { dateFormatter.format(date) }

    private val distanceKm by derivedStateOf { distanceKmInputValue.text.toDoubleOrNull() }
    var distanceKmInputValue by mutableStateOf(value = TextFieldValue(text = ""))
        private set
    val distanceKmInputError by derivedStateOf { distanceKm == null }

    private val duration by derivedStateOf { durationInputFieldState.toTimePeriodOrNull() }
    var durationInputFieldState by mutableStateOf(value = TextFieldValue(text = ""))
        private set
    val durationFieldError by derivedStateOf { duration == null }

    private val averagePace by derivedStateOf { averagePaceInputFieldState.toTimePeriodOrNull() }
    var averagePaceInputFieldState by mutableStateOf(value = TextFieldValue(text = ""))
        private set
    val averagePaceFieldError by derivedStateOf { averagePace == null }

    var screenState by mutableStateOf(value = UiState(loading = false))

    init {
        viewModelScope.launch {
            workout.value = workoutRepository.findById(workoutId = workoutId)
            val workout = workout.value

            distanceKmInputValue = distanceKmInputValue.copy(text = workout.distanceKm.toString())
            durationInputFieldState = durationInputFieldState.copy(text = workout.duration.toString())
            averagePaceInputFieldState = averagePaceInputFieldState.copy(text = workout.averagePace.toString())
        }
    }

    fun updateDate(input: String) {
        workout.value = workout.value.copy(date = dateFormatter.parse(input)!!)
    }

    fun updateDistance(input: TextFieldValue) {
        val text = input.text.take(n = 5)
        distanceKmInputValue = input.copy(text =  text, selection = TextRange(index = text.length))
    }

    fun updateDuration(input: TextFieldValue) {
        durationInputFieldState = input.formatAsTimePeriod()
    }

    fun updateAveragePace(input: TextFieldValue) {
        averagePaceInputFieldState = input.formatAsTimePeriod()
    }

    fun updateWorkout() {
        screenState = screenState.copy(loading = true)

        val distanceKm = distanceKm
        val duration = duration
        val averagePace = averagePace

        if (distanceKm == null) {
            Log.e("Matias", "Return distanceKm == null")
            return
        }

        if (duration == null) {
            Log.e("Matias", "Return duration == null")
            return
        }

        if (averagePace == null) {
            Log.e("Matias", "Return averagePace == null")
            return
        }

        viewModelScope.launch {
            workoutRepository.update(workout = workout.value.copy(
                date = date,
                distanceKm = distanceKm,
                duration = duration,
                averagePace = averagePace,
            ))

            screenState = screenState.copy(loading = false, finish = true)
        }
    }

    private fun TextFieldValue.toTimePeriodOrNull() = try {
        val textSplit = text.split(":")

        TimePeriod(minutes = textSplit[0].toInt(), seconds = textSplit[1].toInt())
    } catch (e: NumberFormatException) {
        null
    }
    catch (e: IndexOutOfBoundsException) {
        null
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
