package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDetailData(
    val id: String,
    val userId: String,
    var name: String,
    val duration: Int,
    val notes: String? = null,
    val date: String,
    val isCompleted: Boolean,
    val tags: List<TagEnum>? = emptyList(),
    val createdAt: String,
    val updatedAt: String,
    val workoutExercises: List<WorkoutExerciseData> = emptyList()
)