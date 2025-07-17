package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.data.models.response.WorkoutExerciseData
import com.jmoriba.muscletrack.data.models.response.WorkoutSummaryData
import com.jmoriba.muscletrack.network.repository.WorkoutRepository
import com.jmoriba.muscletrack.network.api.ApiRoutes
import com.jmoriba.muscletrack.network.api.HttpClientProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WorkoutRepositoryImpl(clientProvider: HttpClientProvider) : WorkoutRepository {

    private val client: HttpClient = clientProvider.privateClient

    override suspend fun getWorkouts(): List<WorkoutSummaryData> {
        return try {
            client.get("${ApiRoutes.BASE_URL}${ApiRoutes.Workouts.All}").body()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override fun editWorkout(workout: WorkoutExerciseData) {

    }

    override fun createWorkout(workout: WorkoutExerciseData) {

    }
}