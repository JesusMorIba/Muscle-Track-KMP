package com.jmoriba.muscletrack.feature.home.presentation

import com.jmoriba.muscletrack.data.repository.ReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeViewModel(private val repository: ReportRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun handle(event: HomeEvent) {
        when (event) {
            is HomeEvent.EmailChanged -> onEmailChange(event.email)
            is HomeEvent.PasswordChanged -> onPasswordChange(event.password)
            is HomeEvent.TogglePasswordVisibility -> onTogglePasswordVisibility()
            is HomeEvent.RememberMeChanged -> onRememberMeChange(event.checked)
            is HomeEvent.HomeClicked -> onHomeClick()
        }
    }

    private fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    private fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    private fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    private fun onRememberMeChange(checked: Boolean) {
        _uiState.update { it.copy(rememberMe = checked) }
    }

    private fun onHomeClick() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        viewModelScope.launch {
            if (email.isNotBlank() && password.isNotBlank()) {
            }
        }
    }
}

data class HomeUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

sealed interface HomeEvent {
    data class EmailChanged(val email: String) : HomeEvent
    data class PasswordChanged(val password: String) : HomeEvent
    object TogglePasswordVisibility : HomeEvent
    data class RememberMeChanged(val checked: Boolean) : HomeEvent
    object HomeClicked : HomeEvent
}
