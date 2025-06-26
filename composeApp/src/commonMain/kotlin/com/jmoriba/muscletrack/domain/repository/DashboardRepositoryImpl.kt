package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.data.models.response.DashboardData
import com.jmoriba.muscletrack.data.models.response.MuscleGroupStatData
import com.jmoriba.muscletrack.data.models.response.WorkoutData
import com.jmoriba.muscletrack.data.repository.DashboardRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.minus
import kotlinx.datetime.DatePeriod

class DashboardRepositoryImpl(private val supabaseClient: SupabaseClient) : DashboardRepository {

    override suspend fun getDashboardData(): DashboardData? {
        return try {
            val response = supabaseClient.postgrest
                .rpc("get_dashboard_data")
                .decodeAs<DashboardData>()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getMuscleGroupBalance(): List<MuscleGroupStatData?> {
        return try {
            val response = supabaseClient.postgrest
                .rpc("get_all_muscle_stats")
                .decodeList<MuscleGroupStatData>()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getRecentWorkouts(): List<WorkoutData?> {
        return try {

            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
            val sevenDaysAgo = now.minus(DatePeriod(days = 7)).toString()

            val response = supabaseClient
                .from("workout")
                .select {
                    filter {
                        gte("date", sevenDaysAgo)
                    }
                }
                .decodeList<WorkoutData>()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
