package com.jmoriba.muscletrack.data.models.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DashboardData(
    @SerialName("total_workouts")
    val totalWorkouts: Long,
    @SerialName("completed_workouts")
    val completedWorkouts: Long,
    @SerialName("total_exercises")
    val totalExercises: Long,
    @SerialName("exercises_with_pr")
    val exercisesWithPR: Long,
    @SerialName("recent_activity")
    val recentActivity: Long
) {
    companion object {
        fun default(): DashboardData {
            return DashboardData(
                totalWorkouts = 0L,
                completedWorkouts = 0L,
                totalExercises = 0L,
                exercisesWithPR = 0L,
                recentActivity = 0L
            )
        }
    }
}
