package com.jmoriba.muscletrack.data.repository

import com.jmoriba.muscletrack.data.models.response.ExerciseData

interface ExerciseRepository {

    suspend fun getExercises(): List<ExerciseData>?

}