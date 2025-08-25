package com.jmoriba.muscletrack.feature.dashboard.presentation

import com.jmoriba.muscletrack.common.utils.ErrorHandler
import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.network.model.response.DashboardResponse
import com.jmoriba.muscletrack.network.repository.DashboardRepository
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
    }

    fun handle(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.GetWeeklySummary -> getDashboardData()
        }
    }

    private fun getDashboardData() {
        viewModelScope.launch {
            _uiState.update { it.copy(dashboardData = Resource.Loading) }

            try {
                val result = repository.getDashboardData()

                _uiState.update { it.copy(dashboardData = Resource.Success(result)) }

            } catch (e: Exception) {
                val error = ErrorHandler.handleException(e)
                _uiState.update { it.copy(dashboardData = Resource.Error(error)) }
            }
        }
    }

}

data class DashboardUiState(
    val dashboardData: Resource<DashboardResponse> = Resource.Loading
)

sealed interface DashboardEvent {
    object GetWeeklySummary : DashboardEvent
}
