package com.jmoriba.muscletrack.data.network.model

data class WorkoutData (
    val id: Int,
    val name: String,
    val photo: String?,
    val kcal: Int?,
    val duration: Int
) {
    companion object {
        fun defaultWorkout(): WorkoutData {
            return WorkoutData(
                id = 1,
                name = "Full Body",
                photo = "",
                kcal = 500,
                duration = 45
            )
        }
    }
}