package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutData(
    val id: String,
    val name: String,
    val date: String,
    val duration: Int,
    val isCompleted: Boolean,
    val numberOfExercises: Int,
    val numberOfSets: Int,
    val tags: List<TagEnum> = emptyList(),
)
