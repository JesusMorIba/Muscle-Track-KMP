package com.jmoriba.muscletrack.feature.report.presentation

import com.jmoriba.muscletrack.data.repository.HistoryRepository
import com.jmoriba.muscletrack.extension.getTodayDateString
import com.jmoriba.muscletrack.model.WorkoutModelUI
import com.jmoriba.muscletrack.model.WorkoutModelUI.Companion.toWorkoutModelUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class ReportUiState(
    val workouts: List<WorkoutModelUI?> = emptyList()
)

class ReportViewModel(private val repository: HistoryRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ReportUiState())
    val uiState = _uiState.asStateFlow()
    private val workouts = repository.getWorkoutByDate("")

    init {
        getWorkoutByDate(getTodayDateString())
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