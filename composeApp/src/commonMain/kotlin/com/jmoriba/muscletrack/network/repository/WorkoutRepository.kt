package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.network.model.response.WorkoutData

interface WorkoutRepository {

    suspend fun getWorkouts(): List<WorkoutData>

    // suspend fun getWorkoutById(id: Int): WorkoutWithExercisesAndSetsData?

    // fun editWorkout(workout: WorkoutExerciseData)

    // fun createWorkout(workout: WorkoutExerciseData)

}