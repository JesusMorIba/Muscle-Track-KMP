package com.jmoriba.muscletrack.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutExerciseResponse(
    val id: String,
    val workoutId: String,
    val exercise: ExerciseResponse,
    val position: Int,
    val exerciseSets: List<ExerciseSetResponse> = emptyList()
)