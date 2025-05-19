package com.jmoriba.muscletrack.data.local.models

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutData (
    val id: String,
    val name: String,
    val photo: String?,
    val kcal: Int?,
    val duration: Int,
    val date: String
) {
    companion object {
        fun defaultWorkout(): WorkoutData {
            return WorkoutData(
                id = "1",
                name = "Full Body",
                photo = "",
                kcal = 500,
                duration = 45,
                date = "23-03-2000"
            )
        }
    }
}