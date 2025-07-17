package com.jmoriba.muscletrack.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSetResponse(
    val id: String,
    val workoutExerciseId: String,
    val weight: Int,
    val reps: Int,
    val pr: Boolean,
    val workoutDate: String
)