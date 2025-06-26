package com.jmoriba.muscletrack.data.repository

import com.jmoriba.muscletrack.data.models.response.DashboardData
import com.jmoriba.muscletrack.data.models.response.MuscleGroupStatData
import com.jmoriba.muscletrack.data.models.response.WorkoutData

interface DashboardRepository {

    suspend fun getDashboardData(): DashboardData?

    suspend fun getMuscleGroupBalance(): List<MuscleGroupStatData?>

    suspend fun getRecentWorkouts(): List<WorkoutData?>
}