package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.data.models.response.WorkoutExerciseData
import com.jmoriba.muscletrack.data.models.response.WorkoutSummaryData
import com.jmoriba.muscletrack.data.repository.WorkoutRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class WorkoutRepositoryImpl(private val supabaseClient: SupabaseClient) : WorkoutRepository {

    override suspend fun getWorkouts(): List<WorkoutSummaryData> {
        return try {
            val response = supabaseClient.postgrest
                .rpc("get_workout_summaries")
                .decodeList<WorkoutSummaryData>()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }


    /* override suspend fun getWorkoutById(id: Int): WorkoutWithExercisesAndSetsData? {
        return try {
            supabaseClient
                .from("workouts")
                .select(
                    Columns.raw(
                        """
                    *,
                    workout_exercises (
                        id,
                        order,
                        exercise:exercise_id (
                            id,
                            name
                        ),
                        exerciseSets:exercise_sets (
                            id,
                            workout_exercise_id,
                            weight,
                            reps,
                            rir
                        )
                    )
                    """.trimIndent()
                    )
                ) {
                    filter {
                        eq("id", id)
                    }
                }
                .decodeList<WorkoutWithExercisesAndSetsData>()
                .firstOrNull()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }*/

    override fun editWorkout(workout: WorkoutExerciseData) {

    }

    override fun createWorkout(workout: WorkoutExerciseData) {

    }
}