package com.jmoriba.muscletrack.feature.exercisedetail.presentation

import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.network.model.entities.ExerciseData
import com.jmoriba.muscletrack.network.model.entities.WorkoutData
import com.jmoriba.muscletrack.network.repository.ExerciseDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel

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
        /*
        viewModelScope.launch {
            val details = repository.getExerciseDetails(exerciseId)
            val workouts = repository.getWorkoutHistoryForExercise(exerciseId)
            _uiState.value = ExerciseDetailUiState(
                selectedExercise = details,
                workoutHistory = workouts.filterNotNull()
            )
        }*/
    }
}

data class ExerciseDetailUiState(
    val selectedExercise: Resource<ExerciseData> = Resource.Loading,
    val workoutHistory: Resource<List<WorkoutData>> = Resource.Loading
)

sealed interface ExerciseDetailEvent {
    data class GetExerciseById(val exerciseId: String) : ExerciseDetailEvent
}