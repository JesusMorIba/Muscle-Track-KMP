package com.jmoriba.muscletrack.network.model.request

import com.jmoriba.muscletrack.network.model.entities.MuscleEnum
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseRequest(
    val name: String,
    val equipmentId: Int,
    val target: Int? = null,
    val description: String? = null,
    val categoryId: Int,
    val primaryMuscle: MuscleEnum,
    val secondaryMuscles: List<MuscleEnum> = emptyList()
)