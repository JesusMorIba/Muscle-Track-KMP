package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.network.repository.AuthRepository
import com.jmoriba.muscletrack.network.api.HttpClientProvider

class AuthRepositoryImpl(clientProvider: HttpClientProvider) : AuthRepository {

    private val client = clientProvider.privateClient

   /* override suspend fun signIn(email: String, password: String): AuthResultUIState {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            AuthResultUIState.Success
        } catch (e: RestException) {
            AuthResultUIState.Error(e.message ?: "Login failed. Please check your credentials.")
        } catch (e: Exception) {
            AuthResultUIState.Error("An unexpected error occurred. Please try again.")
        }
    }

    override suspend fun signUp(email: String, password: String): AuthResultUIState {
        return try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            AuthResultUIState.Success
        } catch (e: RestException) {
            AuthResultUIState.Error(e.message ?: "Registration failed. Please try again.")
        } catch (e: Exception) {
            AuthResultUIState.Error("An unexpected error occurred. Please try again.")
        }
    }

    override suspend fun signOut(): AuthResultUIState {
        return try {
            auth.signOut()
            AuthResultUIState.Success
        } catch (e: Exception) {
            AuthResultUIState.Error("Failed to sign out. Please try again.")
        }
    }

    override suspend fun resetPassword(email: String): AuthResultUIState {
        return try {
            auth.resetPasswordForEmail(email)
            AuthResultUIState.Success
        } catch (e: Exception) {
            AuthResultUIState.Error("Failed to send reset email. Please try again.")
        }
    }

    override suspend fun getCurrentUser(): UserInfo? {
        return try {
            auth.retrieveUserForCurrentSession()
        } catch (e: Exception) {
            null
        }
    } */

    override suspend fun isUserLoggedIn(): Boolean {
        return false
    }
}
