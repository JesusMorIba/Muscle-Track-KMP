package com.jmoriba.muscletrack.data.network.model

import ExerciseData

data class WorkoutDetailData (
    val id: Int,
    val name: String,
    val photo: String?,
    val kcal: Int?,
    val duration: Int,
    val notes: String?,
    val exercises: List<ExerciseData>,
) {
    companion object {
        fun defaultWorkoutDetail(): WorkoutDetailData {
            return WorkoutDetailData(
                id = 0,
                name = "Full Body",
                photo = "",
                kcal = 500,
                duration = 45,
                notes = "Best Workout",
                exercises = listOf(ExerciseData.defaultExercise())
            )
        }
    }
}