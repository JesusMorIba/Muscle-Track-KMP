package com.jmoriba.muscletrack.feature.dashboard.presentation

import com.jmoriba.muscletrack.data.models.response.DashboardData
import com.jmoriba.muscletrack.data.models.entities.MuscleGroup
import com.jmoriba.muscletrack.data.models.response.MuscleGroupStatData
import com.jmoriba.muscletrack.data.models.response.WorkoutData
import com.jmoriba.muscletrack.data.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class DashboardViewModel(private val repository: DashboardRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        getDashboardData()
        getMuscleGroupStat()
        getRecentWorkouts()
    }

    fun handle(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.GetWeeklySummary -> getDashboardData()
        }
    }

    private fun getDashboardData() {
        viewModelScope.launch {
            val result = repository.getDashboardData()
            _uiState.update { state ->
                state.copy(dashboardData = result ?: DashboardData.default())
            }
        }
    }

    private fun getMuscleGroupStat() {
        viewModelScope.launch {
            val result = repository.getMuscleGroupBalance()
            val safeList = result.filterNotNull()
            val (_, values) = processMuscleStats(safeList)

            _uiState.update { state ->
                state.copy(
                    muscleStats = safeList,
                    muscleValues = values
                )
            }
        }
    }

    private fun getRecentWorkouts() {
        viewModelScope.launch {
            val result = repository.getRecentWorkouts()
            _uiState.update { state ->
                state.copy(recentWorkouts = result.filterNotNull())
            }
        }
    }

    private fun processMuscleStats(stats: List<MuscleGroupStatData>): Pair<List<String>, List<Double>> {
        val groupedByMuscle: Map<MuscleGroup, Double> = stats
            .groupBy(
                keySelector = { it.muscleName },
                valueTransform = { it.totalReps.toDouble() }
            )
            .mapValues { it.value.sum() }

        val maxVal = groupedByMuscle.values.maxOrNull()?.takeIf { it > 0 } ?: 1.0

        val normalizedValues = MuscleGroup.entries.map { muscle ->
            val raw = groupedByMuscle[muscle] ?: 0.0
            (raw / maxVal) * 50.0
        }

        val labels = MuscleGroup.entries.map { it.displayName }

        return labels to normalizedValues
    }

}

data class DashboardUiState(
    val dashboardData: DashboardData = DashboardData.default(),
    val muscleStats: List<MuscleGroupStatData?> = emptyList(),
    val recentWorkouts: List<WorkoutData> = emptyList(),
    val muscleValues: List<Double> = emptyList()
)

sealed interface DashboardEvent {
    object GetWeeklySummary : DashboardEvent
}
