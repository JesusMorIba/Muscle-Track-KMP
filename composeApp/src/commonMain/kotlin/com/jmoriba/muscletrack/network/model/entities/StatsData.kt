package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsData(
    @SerialName("total_workouts")
    val totalWorkouts: Int,

    @SerialName("completed_workouts")
    val completedWorkouts: Int,

    @SerialName("total_exercises")
    val totalExercises: Int,

    @SerialName("exercises_with_pr")
    val exercisesWithPr: Int,

    @SerialName("recent_activity")
    val recentActivity: Int
)

fun StatsData.getCompletionRate(): Float {
    return if (totalWorkouts > 0) {
        (completedWorkouts.toFloat() / totalWorkouts.toFloat()) * 100f
    } else {
        0f
    }
}