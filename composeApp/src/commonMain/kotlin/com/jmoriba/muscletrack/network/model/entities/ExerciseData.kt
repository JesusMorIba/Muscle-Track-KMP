package com.jmoriba.muscletrack.network.model.entities

import com.jmoriba.muscletrack.network.model.response.CategoryResponse
import com.jmoriba.muscletrack.network.model.response.EquipmentResponse
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseData(
    val id: String,
    val name: String,
    val equipmentId: Int,
    val target: Int? = null,
    val description: String? = null,
    val categoryId: Int,
    val createdAt: String,
    val updatedAt: String,
    val primaryMuscle: MuscleEnum,
    val secondaryMuscles: List<MuscleEnum> = emptyList(),
    val equipment: EquipmentResponse,
    val category: CategoryResponse
)