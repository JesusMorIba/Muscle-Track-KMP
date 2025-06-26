package com.jmoriba.muscletrack.feature.workout.presentation

import com.jmoriba.muscletrack.data.models.response.WorkoutSummaryData
import com.jmoriba.muscletrack.data.repository.WorkoutRepository
import com.jmoriba.muscletrack.domain.models.WorkoutFilterUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class WorkoutViewModel(private val repository: WorkoutRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getWorkouts()
    }

    fun handle(event: WorkoutEvent) {
        when (event) {
            is WorkoutEvent.FilterChanged -> onFilterChanged(event.filter)
        }
    }

    private fun onFilterChanged(newFilter: WorkoutFilterUIState) {
        _uiState.update { state ->
            val filtered = filterWorkouts(state.allWorkouts, newFilter)
            state.copy(
                selectedFilter = newFilter,
                filteredWorkouts = filtered
            )
        }
    }

    private fun getWorkouts() {
        viewModelScope.launch {
            val result = repository.getWorkouts()
            _uiState.update { state ->
                val filtered = filterWorkouts(result, state.selectedFilter)
                state.copy(
                    allWorkouts = result,
                    filteredWorkouts = filtered
                )
            }
        }
    }

    private fun filterWorkouts(workouts: List<WorkoutSummaryData>, filter: WorkoutFilterUIState): List<WorkoutSummaryData> {
        return when (filter) {
            WorkoutFilterUIState.ALL_WORKOUTS -> workouts
            WorkoutFilterUIState.COMPLETED -> workouts.filter { it.isCompleted }
            WorkoutFilterUIState.IN_PROGRESS -> workouts.filter { !it.isCompleted }
        }
    }
}

data class WorkoutUiState(
    val allWorkouts: List<WorkoutSummaryData> = emptyList(),
    val filteredWorkouts: List<WorkoutSummaryData> = emptyList(),
    val selectedFilter: WorkoutFilterUIState = WorkoutFilterUIState.ALL_WORKOUTS
)

sealed interface WorkoutEvent {
    data class FilterChanged(val filter: WorkoutFilterUIState) : WorkoutEvent
}