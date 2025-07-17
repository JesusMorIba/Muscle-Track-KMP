package com.jmoriba.muscletrack.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutExerciseRequest(
    val workoutId: String,
    val exerciseId: String,
    val position: Int
)