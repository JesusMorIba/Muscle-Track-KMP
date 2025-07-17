package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.network.model.response.ExercisesResponse

interface ExerciseRepository {

    suspend fun getExercises(): ExercisesResponse?

}