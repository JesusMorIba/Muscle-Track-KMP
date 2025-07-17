package com.jmoriba.muscletrack.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ExercisesResponse(
    val exercises: List<ExerciseData>,
    val pagination: PaginationData
)
