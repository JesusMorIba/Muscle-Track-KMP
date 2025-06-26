package com.jmoriba.muscletrack.data.models.response

import com.jmoriba.muscletrack.data.models.entities.MuscleGroup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MuscleGroupStatData(
    @SerialName("muscle_name")
    val muscleName: MuscleGroup,
    @SerialName("total_reps")
    val totalReps: Int
)
