package com.jmoriba.muscletrack.data.repository

import com.jmoriba.muscletrack.data.local.models.WorkoutData
import com.jmoriba.muscletrack.data.local.models.WorkoutDetailData

interface ReportRepository {

    suspend fun getWorkoutByDate(date: String): List<WorkoutData?>

    suspend fun getWorkoutById(id: String): WorkoutDetailData?

    fun editWorkout(workout: WorkoutDetailData)

    fun createWorkout(workout: WorkoutDetailData)

}