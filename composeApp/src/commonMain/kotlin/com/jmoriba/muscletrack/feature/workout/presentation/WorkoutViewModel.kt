package com.jmoriba.muscletrack.feature.workout.presentation

import com.jmoriba.muscletrack.common.utils.ErrorHandler
import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.network.model.entities.WorkoutFilterEnum
import com.jmoriba.muscletrack.network.model.entities.WorkoutData
import com.jmoriba.muscletrack.network.repository.WorkoutRepository
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

    private fun onFilterChanged(newFilter: WorkoutFilterEnum) {
        _uiState.update { state ->
            val filtered = when (val data = state.allWorkouts) {
                is Resource.Success -> {
                    val workouts = data.data
                    Resource.Success(filterWorkouts(workouts, newFilter))
                }
                is Resource.Error -> Resource.Error(data.error)
                is Resource.Loading -> Resource.Loading
            }

            state.copy(
                selectedFilter = newFilter,
                filteredWorkouts = filtered
            )
        }
    }

    private fun getWorkouts() {
        viewModelScope.launch {
            _uiState.update { it.copy(allWorkouts = Resource.Loading) }

            try {
                val result = repository.getWorkouts()

                val filtered = filterWorkouts(result?.workouts ?: emptyList(), _uiState.value.selectedFilter)

                _uiState.update {
                    it.copy(
                        allWorkouts = Resource.Success(result?.workouts ?: emptyList()),
                        filteredWorkouts = Resource.Success(filtered)
                    )
                }
            } catch (e: Exception) {
                val error = ErrorHandler.handleException(e)
                _uiState.update {
                    it.copy(
                        allWorkouts = Resource.Error(error),
                        filteredWorkouts = Resource.Error(error)
                    )
                }
            }
        }
    }

    private fun filterWorkouts(workouts: List<WorkoutData>, filter: WorkoutFilterEnum): List<WorkoutData> {
        return when (filter) {
            WorkoutFilterEnum.ALL_WORKOUTS -> workouts
            WorkoutFilterEnum.COMPLETED -> workouts.filter { it.isCompleted }
            WorkoutFilterEnum.IN_PROGRESS -> workouts.filter { !it.isCompleted }
        }
    }
}

data class WorkoutUiState(
    val allWorkouts: Resource<List<WorkoutData>> = Resource.Loading,
    val filteredWorkouts: Resource<List<WorkoutData>> = Resource.Loading,
    val selectedFilter: WorkoutFilterEnum = WorkoutFilterEnum.ALL_WORKOUTS
)

sealed interface WorkoutEvent {
    data class FilterChanged(val filter: WorkoutFilterEnum) : WorkoutEvent
}