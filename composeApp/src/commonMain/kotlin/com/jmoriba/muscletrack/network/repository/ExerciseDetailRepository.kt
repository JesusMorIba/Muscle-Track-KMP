package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.data.models.response.ExerciseDetailsData
import com.jmoriba.muscletrack.data.models.response.WorkoutExerciseData

interface ExerciseDetailRepository {

    suspend fun getExerciseDetails(id: String): ExerciseDetailsData?

    suspend fun getWorkoutHistoryForExercise(id: String): List<WorkoutExerciseData?>

}