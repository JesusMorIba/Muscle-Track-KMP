package com.jmoriba.muscletrack.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseProgressData(
    @SerialName("workout_date")
    val workoutDate: String,

    @SerialName("max_weight")
    val maxWeight: Int
)