package com.jmoriba.muscletrack.network.model.response

import com.jmoriba.muscletrack.network.model.entities.MuscleStatData
import com.jmoriba.muscletrack.network.model.entities.RecentWorkoutData
import com.jmoriba.muscletrack.network.model.entities.StatsData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponse (
    val stats: StatsData? = null,
    @SerialName("muscle_stats")
    val muscleStats: List<MuscleStatData>? = emptyList(),
    @SerialName("recent_workouts")
    val recentWorkouts: List<RecentWorkoutData>? = emptyList()
)