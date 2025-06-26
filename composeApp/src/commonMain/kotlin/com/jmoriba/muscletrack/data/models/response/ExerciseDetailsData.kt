package com.jmoriba.muscletrack.data.models.response

import com.jmoriba.muscletrack.data.models.entities.MuscleGroup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDetailsData(
    @SerialName("id")
    val exerciseId: String,
    val name: String,
    val description: String?,
    val category: String,
    val target: Int?,
    val equipment: String,
    @SerialName("primary_muscle")
    val primaryMuscle: MuscleGroup,
    @SerialName("secondary_muscles")
    val secondaryMuscles: List<MuscleGroup>?,
    @SerialName("latest_pr")
    val latestPr: Int?,
    val progress: List<ExerciseProgressData?>
)

val ExerciseDetailsData.progress_chart_data: Float
    get() {
        val targetValue = target ?: return 0f
        return if (latestPr != null && targetValue > 0) {
            latestPr.toFloat() / targetValue.toFloat()
        } else {
            0f
        }
    }

