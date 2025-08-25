package com.jmoriba.muscletrack.network.model.response

import com.jmoriba.muscletrack.network.model.entities.PaginationData
import com.jmoriba.muscletrack.network.model.entities.WorkoutData
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutResponse(
    val workouts: List<WorkoutData>,
    val pagination: PaginationData
)
