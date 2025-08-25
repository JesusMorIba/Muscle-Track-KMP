package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.network.repository.ExerciseRepository
import com.jmoriba.muscletrack.network.api.ApiRoutes
import com.jmoriba.muscletrack.network.api.HttpClientProvider
import com.jmoriba.muscletrack.network.model.response.ExerciseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ExerciseRepositoryImpl(clientProvider: HttpClientProvider) : ExerciseRepository {

    private val client: HttpClient = clientProvider.publicClient

    override suspend fun getExercises(): ExerciseResponse? {
        return try {
            client.get("${ApiRoutes.BASE_URL}${ApiRoutes.Exercises.All}").body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}