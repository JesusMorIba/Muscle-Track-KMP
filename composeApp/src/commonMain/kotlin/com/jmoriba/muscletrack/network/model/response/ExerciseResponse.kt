package com.jmoriba.muscletrack.network.model.response

import com.jmoriba.muscletrack.network.model.entities.ExerciseData
import com.jmoriba.muscletrack.network.model.entities.PaginationData
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseResponse(
    val exercises: List<ExerciseData>,
    val pagination: PaginationData
)
