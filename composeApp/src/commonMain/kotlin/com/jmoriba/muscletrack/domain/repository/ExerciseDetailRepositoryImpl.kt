package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.network.repository.ExerciseDetailRepository
import com.jmoriba.muscletrack.network.api.ApiRoutes
import com.jmoriba.muscletrack.network.api.HttpClientProvider
import com.jmoriba.muscletrack.network.model.response.ExerciseData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ExerciseDetailRepositoryImpl(
    clientProvider: HttpClientProvider
) : ExerciseDetailRepository {

    private val client: HttpClient = clientProvider.privateClient

    /*
    override suspend fun getExerciseDetails(id: String): ExerciseData? {
        return try {
            client.get("${ApiRoutes.BASE_URL}${ApiRoutes.Exercises.detail(id)}").body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getWorkoutHistoryForExercise(id: String): List<WorkoutExerciseData> {
        return try {
            client.get("${ApiRoutes.BASE_URL}${ApiRoutes.Exercises.detail(id)}/history").body()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }*/
}
