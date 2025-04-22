package com.jmoriba.muscletrack.model

import com.jmoriba.muscletrack.data.network.model.WorkoutData

data class WorkoutModelUI(
    val id: Int,
    val name: String,
    val photo: String?,
    val kcal: Int?,
    val duration: Int,
) {
    companion object {
        fun defaultWorkoutModelUI(): WorkoutModelUI {
            return WorkoutModelUI(
                id = 0,
                name = "Default Workout",
                photo = "default_photo_url",
                kcal = 0,
                duration = 0
            )
        }

        fun WorkoutData.toWorkoutModelUI(): WorkoutModelUI {
            return WorkoutModelUI(
                id = this.id,
                name = this.name,
                photo = this.photo,
                kcal = this.kcal,
                duration = this.duration
            )
        }
    }
}
