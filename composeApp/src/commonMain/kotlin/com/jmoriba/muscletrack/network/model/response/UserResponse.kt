package com.jmoriba.muscletrack.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val isEmailVerified: Boolean,
    val workouts: List<WorkoutResponse> = emptyList()
)