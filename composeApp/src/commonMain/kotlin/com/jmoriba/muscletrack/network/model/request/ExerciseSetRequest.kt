package com.jmoriba.muscletrack.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSetRequest(
    val workoutExerciseId: String,
    val weight: Int,
    val reps: Int,
    val pr: Boolean = false,
    val workoutDate: String
)