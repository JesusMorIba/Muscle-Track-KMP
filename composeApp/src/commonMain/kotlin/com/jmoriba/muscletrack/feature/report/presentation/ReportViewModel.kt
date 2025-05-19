package com.jmoriba.muscletrack.feature.report.presentation

import com.jmoriba.muscletrack.data.repository.ReportRepository
import com.jmoriba.muscletrack.extension.getTodayDateString
import com.jmoriba.muscletrack.domain.models.WorkoutModelUI
import com.jmoriba.muscletrack.domain.models.WorkoutModelUI.Companion.toWorkoutModelUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ReportViewModel(private val repository: ReportRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ReportUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getWorkoutByDate(getTodayDateString())
    }

    fun handle(event: ReportEvent) {
        when (event) {
            is ReportEvent.DayChanged -> onDayChanged(event.day)
            is ReportEvent.WorkoutClicked -> onWorkoutClick(event.workout)
        }
    }

    private fun onDayChanged(day: LocalDate) {
        _uiState.update { it.copy(selectedDate = day) }
        getWorkoutByDate(date = day.toString())
    }

    private fun onWorkoutClick(workout: WorkoutModelUI) {
        viewModelScope.launch {
            println("Workout clicked: $workout")
        }
    }

    fun getWorkoutByDate(date: String) {
        viewModelScope.launch {
            val workouts = repository.getWorkoutByDate(date)
            _uiState.update { state ->
                state.copy(workouts = workouts.mapNotNull { it?.toWorkoutModelUI() })
            }
        }
    }
}

data class ReportUiState(
    val workouts: List<WorkoutModelUI?> = emptyList(),
    val selectedDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)

sealed interface ReportEvent {
    data class DayChanged(val day: LocalDate) : ReportEvent
    data class WorkoutClicked(val workout: WorkoutModelUI) : ReportEvent
}