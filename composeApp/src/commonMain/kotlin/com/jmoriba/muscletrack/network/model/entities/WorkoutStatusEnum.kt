package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class WorkoutStatusEnum {
    @SerialName("COMPLETED")
    COMPLETED,
    @SerialName("PENDING")
    PENDING
}

fun WorkoutStatusEnum.getDisplayName(): String {
    return when (this) {
        WorkoutStatusEnum.COMPLETED -> "Completado"
        WorkoutStatusEnum.PENDING -> "Pendiente"
    }
}