package com.jmoriba.muscletrack.data.repository

import com.jmoriba.muscletrack.domain.models.AuthResultUIState
import io.github.jan.supabase.auth.user.UserInfo

interface AuthRepository {
    suspend fun signIn(email: String, password: String): AuthResultUIState
    suspend fun signUp(email: String, password: String): AuthResultUIState
    suspend fun signOut(): AuthResultUIState
    suspend fun resetPassword(email: String): AuthResultUIState
    suspend fun getCurrentUser(): UserInfo?
    suspend fun isUserLoggedIn(): Boolean
}