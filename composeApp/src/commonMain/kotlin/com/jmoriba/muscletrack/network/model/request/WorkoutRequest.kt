package com.jmoriba.muscletrack.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutRequest(
    val userId: String,
    val name: String,
    val duration: Int,
    val notes: String? = null,
    val date: String,
    val isCompleted: Boolean = false
)