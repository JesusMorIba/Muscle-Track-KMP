package com.jmoriba.muscletrack.feature.workoutdetail.presentation

import com.jmoriba.muscletrack.data.models.response.WorkoutData
import com.jmoriba.muscletrack.network.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ReportDetailViewModel(private val repository: WorkoutRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getWorkoutById(1)
    }

    fun handle(event: WorkoutDetailEvent) {
        when (event) {
            is WorkoutDetailEvent.DayChanged -> {}
        }
    }

    fun getWorkoutById(id: Int) {
        viewModelScope.launch {
            // val result = repository.getWorkoutById(id)
            _uiState.update { state ->
                state.copy(workout = null)
            }
        }
    }
}

data class WorkoutDetailUiState(
    val workout: WorkoutData ? = null,
    val id: Int = 0
)

sealed interface WorkoutDetailEvent {
    data class DayChanged(val day: LocalDate) : WorkoutDetailEvent
}