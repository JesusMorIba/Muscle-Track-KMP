package com.jmoriba.muscletrack.feature.workoutdetail.presentation

import com.jmoriba.muscletrack.common.utils.AppError
import com.jmoriba.muscletrack.common.utils.ErrorHandler
import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.network.model.entities.WorkoutDetailData
import com.jmoriba.muscletrack.network.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class WorkoutDetailViewModel(private val repository: WorkoutRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkoutDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getWorkoutById("101bb160-0704-4e73-b392-bb07be4a28ef")
    }

    fun handle(event: WorkoutDetailEvent) {
        when (event) {
            is WorkoutDetailEvent.DayChanged -> {
            }
        }
    }

    fun getWorkoutById(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(workout = Resource.Loading) }

            try {
                val response = repository.getWorkoutById(id)

                _uiState.update {
                    it.copy(
                        workout = response?.workout?.let { data ->
                            Resource.Success(data)
                        } ?: Resource.Error(AppError.Custom("Workout not found"))
                    )
                }
            } catch (e: Exception) {
                val error = ErrorHandler.handleException(e)
                _uiState.update {
                    it.copy(workout = Resource.Error(error))
                }
            }
        }
    }
}

data class WorkoutDetailUiState(
    val workout: Resource<WorkoutDetailData> = Resource.Loading,
    val id: String = ""
)

sealed interface WorkoutDetailEvent {
    data class DayChanged(val day: LocalDate) : WorkoutDetailEvent
}