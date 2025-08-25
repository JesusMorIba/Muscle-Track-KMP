package com.jmoriba.muscletrack.network.model.response

import com.jmoriba.muscletrack.network.model.entities.WorkoutDetailData
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDetailResponse(
    val workout: WorkoutDetailData
)