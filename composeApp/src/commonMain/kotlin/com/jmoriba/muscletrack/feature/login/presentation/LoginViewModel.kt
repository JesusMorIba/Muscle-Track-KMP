package com.jmoriba.muscletrack.feature.login.presentation

import com.jmoriba.muscletrack.data.repository.ReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class LoginViewModel(private val repository: ReportRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun handle(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> onEmailChange(event.email)
            is LoginEvent.PasswordChanged -> onPasswordChange(event.password)
            is LoginEvent.TogglePasswordVisibility -> onTogglePasswordVisibility()
            is LoginEvent.RememberMeChanged -> onRememberMeChange(event.checked)
            is LoginEvent.LoginClicked -> onLoginClick()
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

    private fun onLoginClick() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        viewModelScope.launch {
            if (email.isNotBlank() && password.isNotBlank()) {
            }
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

sealed interface LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    object TogglePasswordVisibility : LoginEvent
    data class RememberMeChanged(val checked: Boolean) : LoginEvent
    object LoginClicked : LoginEvent
}
