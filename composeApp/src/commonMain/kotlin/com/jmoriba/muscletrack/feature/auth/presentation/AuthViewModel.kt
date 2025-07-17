package com.jmoriba.muscletrack.feature.auth.presentation

import com.jmoriba.muscletrack.network.repository.AuthRepository
import com.jmoriba.muscletrack.domain.models.AuthResultUIState
import com.jmoriba.muscletrack.domain.models.TextFieldUiState
import com.jmoriba.muscletrack.common.utils.ValidatorUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    init {
        checkCurrentSession()
    }

    fun handle(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged -> updateEmail(event.email)
            is AuthEvent.PasswordChanged -> updatePassword(event.password)
            is AuthEvent.ConfirmPasswordChanged -> updateConfirmPassword(event.confirmPassword)
            is AuthEvent.RememberMeChanged -> updateRememberMe(event.checked)
            is AuthEvent.TogglePasswordVisibility -> togglePasswordVisibility()
            AuthEvent.LoginClicked -> login()
            AuthEvent.RegisterClicked -> register()
            AuthEvent.ForgotPasswordClicked -> forgotPassword()
        }
    }

    private fun checkCurrentSession() {
        viewModelScope.launch {
            val isLoggedIn = repository.isUserLoggedIn()
            _uiState.update { it.copy(isAuthenticated = isLoggedIn) }
        }
    }

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = it.email.copy(text = email, error = null), errorMessage = null, successMessage = null)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(password = it.password.copy(text = password, error = null), errorMessage = null, successMessage = null)
        }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.update {
            it.copy(confirmPassword = confirmPassword, errorMessage = null, successMessage = null)
        }
    }

    fun updateRememberMe(rememberMe: Boolean) {
        _uiState.update { it.copy(rememberMe = rememberMe) }
    }

    private fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun clearMessages() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }

    fun login() {
        val state = _uiState.value

        if (!ValidatorUtils.isValidEmail(state.email.text)) {
            _uiState.update {
                it.copy(email = it.email.copy(error = "Invalid email"), errorMessage = "Invalid email")
            }
            return
        }

        if (!ValidatorUtils.isValidPassword(state.password.text)) {
            _uiState.update {
                it.copy(password = it.password.copy(error = "Invalid password"), errorMessage = "Password must be at least 6 characters long and contain a number")
            }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            when (val result = repository.signIn(state.email.text, state.password.text)) {
                is AuthResultUIState.Success -> _uiState.update {
                    it.copy(isAuthenticated = true, isLoading = false, successMessage = "Inicio de sesión exitoso")
                }
                is AuthResultUIState.Error -> _uiState.update {
                    it.copy(errorMessage = result.message, isLoading = false)
                }
                else -> Unit
            }
        }
    }

    fun register() {
        val state = _uiState.value

        if (!ValidatorUtils.isValidEmail(state.email.text)) {
            _uiState.update {
                it.copy(email = it.email.copy(error = "Email inválido"), errorMessage = "Email inválido")
            }
            return
        }

        if (!ValidatorUtils.isValidPassword(state.password.text)) {
            _uiState.update {
                it.copy(password = it.password.copy(error = "Contraseña inválida"), errorMessage = "La contraseña debe tener al menos 6 caracteres y un número")
            }
            return
        }

        if (!ValidatorUtils.doPasswordsMatch(state.password.text, state.confirmPassword)) {
            _uiState.update {
                it.copy(errorMessage = "Passwords do not match")
            }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            when (val result = repository.signUp(state.email.text, state.password.text)) {
                is AuthResultUIState.Success -> _uiState.update {
                    it.copy(isAuthenticated = true, isLoading = false, successMessage = "Registro exitoso")
                }
                is AuthResultUIState.Error -> _uiState.update {
                    it.copy(errorMessage = result.message, isLoading = false)
                }
                else -> Unit
            }
        }
    }

    fun forgotPassword() {
        val state = _uiState.value

        if (!ValidatorUtils.isValidEmail(state.email.text)) {
            _uiState.update {
                it.copy(email = it.email.copy(error = "Email inválido"), errorMessage = "Email inválido")
            }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            when (val result = repository.resetPassword(state.email.text)) {
                is AuthResultUIState.Success -> _uiState.update {
                    it.copy(successMessage = "Correo de recuperación enviado", isLoading = false)
                }
                is AuthResultUIState.Error -> _uiState.update {
                    it.copy(errorMessage = result.message, isLoading = false)
                }
                else -> Unit
            }
        }
    }
}

data class AuthUiState(
    val email: TextFieldUiState = TextFieldUiState(),
    val password: TextFieldUiState = TextFieldUiState(),
    val confirmPassword: String = "",
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

sealed interface AuthEvent {
    data class EmailChanged(val email: String) : AuthEvent
    data class PasswordChanged(val password: String) : AuthEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : AuthEvent
    data class RememberMeChanged(val checked: Boolean) : AuthEvent
    object TogglePasswordVisibility : AuthEvent
    object LoginClicked : AuthEvent
    object RegisterClicked : AuthEvent
    object ForgotPasswordClicked : AuthEvent
}
