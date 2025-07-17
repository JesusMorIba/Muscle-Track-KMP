package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.domain.models.AuthResultUIState

interface AuthRepository {
    // suspend fun signIn(email: String, password: String): AuthResultUIState
    // suspend fun signUp(email: String, password: String): AuthResultUIState
    // suspend fun signOut(): AuthResultUIState
    // suspend fun resetPassword(email: String): AuthResultUIState
    // suspend fun getCurrentUser(): UserInfo?
    suspend fun isUserLoggedIn(): Boolean
}