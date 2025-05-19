package com.jmoriba.muscletrack.feature.detail.presentation

import com.jmoriba.muscletrack.domain.repository.ReportRepositoryImpl
import com.jmoriba.muscletrack.domain.models.WorkoutDetailModelUI
import com.jmoriba.muscletrack.domain.models.WorkoutDetailModelUI.Companion.toWorkoutDetailModelUI
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

class WorkoutDetailViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutDetailUiState())
    val uiState = _uiState.asStateFlow()


    init {
        getWorkoutById(uiState.value.id)
    }

    fun getWorkoutById(id: Int) {

    }
}