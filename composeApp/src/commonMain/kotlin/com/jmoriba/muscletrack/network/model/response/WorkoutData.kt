package com.jmoriba.muscletrack.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutData(
    val id: String,
    val userId: String,
    val name: String,
    val duration: Int,
    val notes: String? = null,
    val date: String,
    val isCompleted: Boolean,
    val workoutExercises: List<WorkoutExerciseResponse> = emptyList(),
    val workoutTags: List<TagResponse> = emptyList()
)