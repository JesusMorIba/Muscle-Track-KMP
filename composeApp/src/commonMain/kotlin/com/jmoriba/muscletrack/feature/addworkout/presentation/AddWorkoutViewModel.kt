package com.jmoriba.muscletrack.feature.addworkout.presentation

import com.jmoriba.muscletrack.common.utils.ErrorHandler
import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.network.model.entities.ExerciseData
import com.jmoriba.muscletrack.network.model.entities.WorkoutDetailData
import com.jmoriba.muscletrack.network.model.request.Workout
import com.jmoriba.muscletrack.network.model.request.WorkoutExercise
import com.jmoriba.muscletrack.network.repository.AddWorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class AddWorkoutViewModel(private val repository: AddWorkoutRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AddWorkoutUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getExercises()
    }

    private fun getExercises() {
        viewModelScope.launch {
            _uiState.update { it.copy(exercises = Resource.Loading) }
            try {
                val result = repository.getExercises()
                _uiState.update { state ->
                    state.copy(exercises = Resource.Success(result?.exercises ?: emptyList()))
                }
            } catch (e: Exception) {
                val error = ErrorHandler.handleException(e)
                _uiState.update { it.copy(exercises = Resource.Error(error)) }
            }
        }
    }

    fun saveWorkout(
        name: String,
        notes: String,
        date: LocalDate,
        duration: Int?,
        completed: Boolean,
        selectedExercises: List<String>,
        tags: List<String>
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val exercisesData = _uiState.value.exercises
                if (exercisesData is Resource.Success) {
                    val workoutExercises = selectedExercises.mapIndexed { index, exerciseId ->
                        val exerciseInfo = exercisesData.data.find { it.id == exerciseId }
                        WorkoutExercise(
                            exerciseId = exerciseId,
                            exerciseName = exerciseInfo?.name ?: "Unknown Exercise",
                            position = index + 1,
                            sets = emptyList() // Sets will be added later when editing the workout
                        )
                    }

                    val workout = Workout(
                        id = _uiState.value.initialWorkout?.id
                            ?: "",
                        name = name,
                        notes = notes,
                        date = date,
                        duration = duration,
                        isCompleted = completed,
                        exercises = workoutExercises,
                        tags = tags
                    )

                    // Call repository to save workout
                    // repository.saveWorkout(workout)

                    _uiState.update { it.copy(isLoading = false, workoutSaved = true) }
                }
            } catch (e: Exception) {
                val error = ErrorHandler.handleException(e)
                // _uiState.update { it.copy(isLoading = false, error = error) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun resetWorkoutSaved() {
        _uiState.update { it.copy(workoutSaved = false) }
    }
}

data class AddWorkoutUiState(
    val initialWorkout: WorkoutDetailData? = null,
    var exercises: Resource<List<ExerciseData>> = Resource.Loading,
    val isLoading: Boolean = false,
    val workoutSaved: Boolean = false,
    val error: String? = null
)

sealed interface AddWorkoutEvent {
    data class DayChanged(val day: LocalDate) : AddWorkoutEvent
}
