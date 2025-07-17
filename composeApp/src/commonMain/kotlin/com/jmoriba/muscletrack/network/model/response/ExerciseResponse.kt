package com.jmoriba.muscletrack.network.model.response

import com.jmoriba.muscletrack.network.model.entities.MuscleEnum
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseResponse(
    val id: String,
    val name: String,
    val equipment: EquipmentResponse,
    val target: Int? = null,
    val description: String? = null,
    val category: CategoryResponse,
    val primaryMuscle: MuscleEnum,
    val secondaryMuscles: List<MuscleEnum> = emptyList()
)