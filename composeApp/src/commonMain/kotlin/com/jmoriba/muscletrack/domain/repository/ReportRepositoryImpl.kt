package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.data.local.models.WorkoutData
import com.jmoriba.muscletrack.data.local.models.WorkoutDetailData
import com.jmoriba.muscletrack.data.repository.ReportRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from


class ReportRepositoryImpl(private val supabaseClient: SupabaseClient) : ReportRepository {

    override suspend fun getWorkoutByDate(date: String): List<WorkoutData?> {
        return try {
            val response = supabaseClient
                .from("workouts")
                .select {
                    filter {
                        eq("date", date)
                    }
                }
                .decodeList<WorkoutData>()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getWorkoutById(id: String): WorkoutDetailData? {
        return try {
            supabaseClient
                .from("workouts")
                .select {
                    filter {
                        eq("id", id)
                    }
                    single()
                }
                .decodeSingle<WorkoutDetailData>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun editWorkout(workout: WorkoutDetailData) {

    }

    override fun createWorkout(workout: WorkoutDetailData) {

    }
}