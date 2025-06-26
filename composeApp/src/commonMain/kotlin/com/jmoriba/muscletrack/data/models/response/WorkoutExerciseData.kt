package com.jmoriba.muscletrack.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutExerciseData(
    val id: String,
    val name: String,
    val date: String,
    @SerialName("total_sets")
    val totalSets: Int,
    @SerialName("max_weight")
    val maxWeight: Int
)
