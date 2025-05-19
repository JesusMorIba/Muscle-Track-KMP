package com.jmoriba.muscletrack.domain.models

import ExerciseData
import com.jmoriba.muscletrack.data.local.models.WorkoutDetailData
import com.jmoriba.muscletrack.data.network.models.ExerciseSetData

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
                exercises = this.exercises.map { it.toExercise() },
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

fun ExerciseData.toExercise(): WorkoutDetailModelUI.Exercise {
    return WorkoutDetailModelUI.Exercise(
        name = this.name,
        sets = this.sets.map { it.toExerciseSet() }
    )
}

fun ExerciseSetData.toExerciseSet(): WorkoutDetailModelUI.Exercise.ExerciseSet {
    return WorkoutDetailModelUI.Exercise.ExerciseSet(
        weight = this.weight,
        reps = this.reps,
        rir = this.rir
    )
}
