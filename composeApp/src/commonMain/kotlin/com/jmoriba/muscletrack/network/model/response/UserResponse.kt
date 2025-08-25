package com.jmoriba.muscletrack.network.model.response

import com.jmoriba.muscletrack.network.model.entities.WorkoutData
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val isEmailVerified: Boolean,
    val workouts: List<WorkoutData> = emptyList()
)