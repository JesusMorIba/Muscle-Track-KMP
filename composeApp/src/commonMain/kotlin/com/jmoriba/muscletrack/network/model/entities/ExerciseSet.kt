package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSet(
    val setNumber: Int,
    val type: String,
    val weight: String,
    val reps: Int,
    val isCompleted: Boolean
)