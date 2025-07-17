package com.jmoriba.muscletrack.network.model.response

import com.jmoriba.muscletrack.network.model.entities.MuscleEnum
import com.jmoriba.muscletrack.network.model.entities.WorkoutStatusEnum
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponse(
    @SerialName("stats")
    val stats: WorkoutStats,
    @SerialName("muscle_stats")
    val muscleStats: List<MuscleGroupStats>,
    @SerialName("recent_workouts")
    val recentWorkouts: List<RecentWorkout>
)

@Serializable
data class WorkoutStats(
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
)

@Serializable
data class MuscleGroupStats(
    @SerialName("muscle_name")
    val muscleName: MuscleEnum,
    @SerialName("total_reps")
    val totalReps: Int
)

@Serializable
data class RecentWorkout(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("date")
    val date: String, // ISO 8601 format
    @SerialName("status")
    val status: WorkoutStatusEnum
)

fun WorkoutStats.getCompletionRate(): Float {
    return if (totalWorkouts > 0) {
        (completedWorkouts.toFloat() / totalWorkouts.toFloat()) * 100f
    } else {
        0f
    }
}

fun RecentWorkout.getFormattedDate(): String {
    return try {
        val instant = kotlinx.datetime.Instant.parse(date)
        val localDateTime = instant.toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
        "${localDateTime.dayOfMonth}/${localDateTime.monthNumber}/${localDateTime.year}"
    } catch (e: Exception) {
        date
    }
}