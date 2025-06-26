package com.jmoriba.muscletrack.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSetData(
    val id: String,
    @SerialName("workout_exercise_id") val workoutExerciseId: String,
    val weight: Int,
    val reps: Int,
    val pr: Boolean,
    @SerialName("workout_date") val workoutDate: String,
    @SerialName("created_at") val createdAt: String? = null
) {
    companion object {
        fun defaultExerciseSet(): ExerciseSetData {
            return ExerciseSetData(
                id = "",
                workoutExerciseId = "",
                weight = 60,
                reps = 10,
                pr = false,
                workoutDate = "1970-01-01",
                createdAt = null
            )
        }
    }
}