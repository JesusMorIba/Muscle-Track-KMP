package com.jmoriba.muscletrack.data.local.models

import ExerciseData

data class WorkoutDetailData (
    val id: Int,
    val name: String,
    val photo: String?,
    val kcal: Int?,
    val duration: Int,
    val notes: String?,
    val date: String,
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
                date = "23-03-2000",
                exercises = listOf(ExerciseData.defaultExercise())
            )
        }
    }
}