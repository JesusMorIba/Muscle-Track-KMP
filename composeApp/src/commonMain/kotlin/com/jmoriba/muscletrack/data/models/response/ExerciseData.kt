package com.jmoriba.muscletrack.data.models.response

import com.jmoriba.muscletrack.data.models.entities.MuscleGroup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseData(
    @SerialName("id")
    val exerciseId: String,
    val name: String,
    val category: String,
    val target: Int?,
    val equipment: String,
    @SerialName("primary_muscle")
    val primaryMuscle: MuscleGroup,
    @SerialName("secondary_muscle")
    val secondaryMuscles: List<MuscleGroup?>,
    @SerialName("latest_pr")
    val latestPr: Int?,
)

val ExerciseData.progress: Float
    get() {
        val targetValue = target ?: return 0f
        return if (latestPr != null && targetValue > 0) {
            latestPr.toFloat() / targetValue.toFloat()
        } else {
            0f
        }
    }

