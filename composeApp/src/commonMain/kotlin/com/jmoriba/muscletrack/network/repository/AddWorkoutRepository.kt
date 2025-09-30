package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.network.model.response.ExerciseResponse

interface AddWorkoutRepository {

    suspend fun getExercises(): ExerciseResponse?

}