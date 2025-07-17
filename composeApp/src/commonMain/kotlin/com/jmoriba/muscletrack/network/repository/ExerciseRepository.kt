package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.data.models.response.ExerciseData

interface ExerciseRepository {

    suspend fun getExercises(): List<ExerciseData>?

}