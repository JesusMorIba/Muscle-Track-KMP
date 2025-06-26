package com.jmoriba.muscletrack.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutSummaryData(
    val id: String,
    @SerialName("workout_name")
    val workoutName: String,
    val date: String,
    val duration: Int,
    @SerialName("is_completed")
    val isCompleted: Boolean,
    val tags: List<String>? = null,
    @SerialName("exercise_count")
    val exerciseCount: Int,
    @SerialName("set_count")
    val setCount: Int
)