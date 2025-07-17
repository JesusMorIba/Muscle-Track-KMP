package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.data.models.response.WorkoutExerciseData
import com.jmoriba.muscletrack.data.models.response.WorkoutSummaryData

interface WorkoutRepository {

    suspend fun getWorkouts(): List<WorkoutSummaryData>

    // suspend fun getWorkoutById(id: Int): WorkoutWithExercisesAndSetsData?

    fun editWorkout(workout: WorkoutExerciseData)

    fun createWorkout(workout: WorkoutExerciseData)

}