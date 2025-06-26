package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.data.models.response.ExerciseDetailsData
import com.jmoriba.muscletrack.data.models.response.WorkoutExerciseData
import com.jmoriba.muscletrack.data.repository.ExerciseDetailRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

class ExerciseDetailRepositoryImpl (private val supabaseClient: SupabaseClient) : ExerciseDetailRepository {

    override suspend fun getExerciseDetails(id: String): ExerciseDetailsData? {
        val params = buildJsonObject {
            put("ex_id", JsonPrimitive(id))
        }

        return try {
            val response = supabaseClient.postgrest
                .rpc("get_exercise_details", params)
                .decodeAs<ExerciseDetailsData>()

            println(response)
            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getWorkoutHistoryForExercise(id: String): List<WorkoutExerciseData?> {
        val params = buildJsonObject {
            put("ex_id", JsonPrimitive(id))
        }


        return try {
            val response = supabaseClient.postgrest
                .rpc("get_workout_exercise_data", params)
                .decodeList<WorkoutExerciseData>()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}