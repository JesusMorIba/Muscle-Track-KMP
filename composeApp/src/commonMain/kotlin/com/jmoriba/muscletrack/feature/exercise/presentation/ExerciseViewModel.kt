package com.jmoriba.muscletrack.feature.exercise.presentation

import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.network.model.entities.EquipmentEnum
import com.jmoriba.muscletrack.network.model.entities.MuscleEnum
import com.jmoriba.muscletrack.network.model.response.ExerciseData
import com.jmoriba.muscletrack.network.repository.ExerciseRepository
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
                state.copy(allExercises = Resource.Success(result?.exercises ?: emptyList()))
            }
            applyFilters()
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        applyFilters()
    }

    fun onMuscleGroupSelected(group: MuscleEnum?) {
        _uiState.update { it.copy(selectedMuscleGroup = group) }
        applyFilters()
    }

    fun onEquipmentSelected(equipment: EquipmentEnum?) {
        _uiState.update { it.copy(selectedEquipment = equipment) }
        applyFilters()
    }

    private fun applyFilters() {
        _uiState.update { state ->
            val allExercises = (state.allExercises as? Resource.Success)?.data ?: emptyList()

            val filtered = allExercises.filter { exercise ->
                val matchesMuscleGroup = state.selectedMuscleGroup == null ||
                        exercise.primaryMuscle.name.equals(state.selectedMuscleGroup.name, ignoreCase = true)

                val matchesEquipment = state.selectedEquipment == null ||
                        exercise.equipment.name.equals(state.selectedEquipment.name, ignoreCase = true)

                val matchesSearchQuery = state.searchQuery.isBlank() ||
                        exercise.name.contains(state.searchQuery, ignoreCase = true)

                matchesMuscleGroup && matchesEquipment && matchesSearchQuery
            }

            state.copy(filteredExercises = filtered)
        }
    }

}

data class ExerciseUiState(
    val allExercises: Resource<List<ExerciseData>> = Resource.Loading,
    val filteredExercises: List<ExerciseData> = emptyList(),
    val selectedMuscleGroup: MuscleEnum? = null,
    val selectedEquipment: EquipmentEnum? = null,
    val searchQuery: String = ""
)

sealed interface ExerciseEvent {
    object FilterChanged : ExerciseEvent
}