package com.jmoriba.muscletrack.feature.detail.presentation

import com.jmoriba.muscletrack.data.repository.HistoryRepository
import com.jmoriba.muscletrack.model.WorkoutDetailModelUI
import com.jmoriba.muscletrack.model.WorkoutDetailModelUI.Companion.toWorkoutDetailModelUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class WorkoutDetailUiState(
    val workout: WorkoutDetailModelUI ? = null,
    val id: Int = 0
)

class WorkoutDetailViewModel(private val repository: HistoryRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutDetailUiState())
    val uiState = _uiState.asStateFlow()
    private val workout = repository.getWorkoutByDate("")

    init {
        getWorkoutById(uiState.value.id)
    }

    fun getWorkoutById(id: Int) {
        viewModelScope.launch {
            val workout = repository.getWorkoutById(id)
            _uiState.update { state ->
                state.copy(workout = workout?.toWorkoutDetailModelUI())
            }
        }
    }
}