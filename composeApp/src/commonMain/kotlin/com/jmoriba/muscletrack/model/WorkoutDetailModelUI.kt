package com.jmoriba.muscletrack.model

import com.jmoriba.muscletrack.data.network.model.WorkoutDetailData

data class WorkoutDetailModelUI(
    val id: Int,
    val name: String,
    val photo: String?,
    val kcal: Int?,
    val duration: Int,
    val notes: String?,
    val exercises: List<Exercise>,
) {
    companion object {
        fun defaultWorkoutModelUI(): WorkoutDetailModelUI {
            return WorkoutDetailModelUI(
                id = 0,
                name = "Default Workout",
                photo = "default_photo_url",
                kcal = 0,
                duration = 0,
                notes = "",
                exercises = emptyList()
            )
        }

        fun WorkoutDetailData.toWorkoutDetailModelUI(): WorkoutDetailModelUI {
            return WorkoutDetailModelUI(
                id = this.id,
                name = this.name,
                photo = this.photo,
                kcal = this.kcal,
                duration = this.duration,
                notes = this.notes,
                exercises = emptyList(),
            )
        }
    }

    data class Exercise(
        val name: String,
        val sets: List<ExerciseSet> = emptyList()
    ) {
        data class ExerciseSet(
            val weight: Int,
            val reps: Int,
            val rir: Int
        )
    }
}
