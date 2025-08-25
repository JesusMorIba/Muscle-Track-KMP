package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.network.model.response.WorkoutDetailResponse
import com.jmoriba.muscletrack.network.model.response.WorkoutResponse

interface WorkoutRepository {

    suspend fun getWorkouts(): WorkoutResponse?

    suspend fun getWorkoutById(id: String): WorkoutDetailResponse?

    // fun editWorkout(workout: WorkoutExerciseData)

    // fun createWorkout(workout: WorkoutExerciseData)

}