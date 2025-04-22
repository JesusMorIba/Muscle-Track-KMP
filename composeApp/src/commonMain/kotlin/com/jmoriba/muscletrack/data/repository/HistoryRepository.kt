package com.jmoriba.muscletrack.data.repository

import com.jmoriba.muscletrack.data.network.model.WorkoutData
import com.jmoriba.muscletrack.data.network.model.WorkoutDetailData

object HistoryRepository {

    fun getWorkoutByDate(date: String): List<WorkoutData?> {
        return listOf(
            WorkoutData(
                id = 0,
                name = "Default Workout",
                photo = "https://i.imgur.com/u3ZefLb.png",
                kcal = 0,
                duration = 0
            )
        )
    }

    fun getWorkoutById(id: Int): WorkoutDetailData? {
        return WorkoutDetailData(
            id = 1,
            name = "Default Workout",
            photo = "https://i.imgur.com/u3ZefLb.png",
            kcal = 0,
            duration = 0,
            notes = "",
            exercises = listOf(ExerciseData.defaultExercise())
        )
    }

    fun editWorkout() {

    }

    fun createWorkout() {

    }
}