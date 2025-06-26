package com.jmoriba.muscletrack.domain.models

sealed class AuthResultUIState {
    object Success : AuthResultUIState()
    data class Error(val message: String) : AuthResultUIState()
    object Loading : AuthResultUIState()
}