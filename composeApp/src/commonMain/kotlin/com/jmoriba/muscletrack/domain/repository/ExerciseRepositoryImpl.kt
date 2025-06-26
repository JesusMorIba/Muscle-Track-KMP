package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.data.models.response.ExerciseData
import com.jmoriba.muscletrack.data.repository.ExerciseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class ExerciseRepositoryImpl(private val supabaseClient: SupabaseClient) : ExerciseRepository {

    override suspend fun getExercises(): List<ExerciseData>? {
        return try {
            val response = supabaseClient.postgrest
                .rpc("get_exercise_summaries")
                .decodeList<ExerciseData>()

            println(response)

            response
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}