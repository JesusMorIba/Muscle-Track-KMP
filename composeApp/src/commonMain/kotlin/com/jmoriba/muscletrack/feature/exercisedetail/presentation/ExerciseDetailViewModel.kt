package com.jmoriba.muscletrack.feature.exercisedetail.presentation

import com.jmoriba.muscletrack.data.models.response.ExerciseDetailsData
import com.jmoriba.muscletrack.data.models.response.WorkoutExerciseData
import com.jmoriba.muscletrack.data.repository.ExerciseDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ExerciseDetailViewModel(private val repository: ExerciseDetailRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ExerciseDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {

    }

    fun handle(event: ExerciseDetailEvent) {
        when (event) {
            is ExerciseDetailEvent.GetExerciseById -> loadExerciseDetail(event.exerciseId)
        }
    }

    fun loadExerciseDetail(exerciseId: String) {
        viewModelScope.launch {
            val details = repository.getExerciseDetails(exerciseId)
            val workouts = repository.getWorkoutHistoryForExercise(exerciseId)
            _uiState.value = ExerciseDetailUiState(
                selectedExercise = details,
                workoutHistory = workouts.filterNotNull()
            )
        }
    }
}

data class ExerciseDetailUiState(
    val selectedExercise: ExerciseDetailsData? = null,
    val workoutHistory: List<WorkoutExerciseData?> = emptyList()
)

sealed interface ExerciseDetailEvent {
    data class GetExerciseById(val exerciseId: String) : ExerciseDetailEvent
}