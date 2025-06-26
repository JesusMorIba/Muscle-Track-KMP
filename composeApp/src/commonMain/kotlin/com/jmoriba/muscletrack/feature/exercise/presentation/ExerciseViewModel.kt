package com.jmoriba.muscletrack.feature.exercise.presentation

import com.jmoriba.muscletrack.data.models.entities.Equipment
import com.jmoriba.muscletrack.data.models.response.ExerciseData
import com.jmoriba.muscletrack.data.models.entities.MuscleGroup
import com.jmoriba.muscletrack.data.repository.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ExerciseUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getExercises()
    }

    fun handle(event: ExerciseEvent) {
        when (event) {
            is ExerciseEvent.FilterChanged -> applyFilters()
        }
    }

    private fun getExercises() {
        viewModelScope.launch {
            val result = repository.getExercises()
            _uiState.update { state ->
                state.copy(allExercises = result ?: emptyList())
            }
            applyFilters()
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        applyFilters()
    }

    fun onMuscleGroupSelected(group: MuscleGroup?) {
        _uiState.update { it.copy(selectedMuscleGroup = group) }
        applyFilters()
    }

    fun onEquipmentSelected(equipment: Equipment?) {
        _uiState.update { it.copy(selectedEquipment = equipment) }
        applyFilters()
    }

    private fun applyFilters() {
        _uiState.update { state ->
            val filtered = state.allExercises.filter { exercise ->
                val matchesMuscleGroup = state.selectedMuscleGroup == null ||
                        exercise.primaryMuscle.displayName.equals(state.selectedMuscleGroup.name, ignoreCase = true)

                val matchesEquipment = state.selectedEquipment == null ||
                        exercise.equipment.equals(state.selectedEquipment.name, ignoreCase = true)

                val matchesSearchQuery = state.searchQuery.isBlank() ||
                        exercise.name.contains(state.searchQuery, ignoreCase = true)

                matchesMuscleGroup && matchesEquipment && matchesSearchQuery
            }
            state.copy(filteredExercises = filtered)
        }
    }
}

data class ExerciseUiState(
    val allExercises: List<ExerciseData> = emptyList(),
    val filteredExercises: List<ExerciseData> = emptyList(),
    val selectedMuscleGroup: MuscleGroup? = null,
    val selectedEquipment: Equipment? = null,
    val searchQuery: String = ""
)

sealed interface ExerciseEvent {
    object FilterChanged : ExerciseEvent
}