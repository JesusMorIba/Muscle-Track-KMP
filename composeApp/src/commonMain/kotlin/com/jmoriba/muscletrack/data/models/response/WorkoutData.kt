package com.jmoriba.muscletrack.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutData(
    val id: String,
    @SerialName("user_id") val userId: String,
    val name: String,
    val duration: Int,
    val notes: String? = null,
    val date: String,
    @SerialName("is_completed") val isCompleted: Boolean,
) {
    companion object {
        fun defaultWorkout(): WorkoutData {
            return WorkoutData(
                id = "1",
                userId = "1",
                name = "Full Body",
                duration = 45,
                notes = "Upper Body",
                date = "2023-03-23",
                isCompleted = false,
            )
        }
    }
}
