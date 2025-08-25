package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.network.repository.WorkoutRepository
import com.jmoriba.muscletrack.network.api.ApiRoutes
import com.jmoriba.muscletrack.network.api.HttpClientProvider
import com.jmoriba.muscletrack.network.model.response.WorkoutDetailResponse
import com.jmoriba.muscletrack.network.model.response.WorkoutResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WorkoutRepositoryImpl(clientProvider: HttpClientProvider) : WorkoutRepository {

    private val client: HttpClient = clientProvider.privateClient

    override suspend fun getWorkouts(): WorkoutResponse? {
        return try {
            client.get("${ApiRoutes.BASE_URL}${ApiRoutes.Workouts.All}").body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getWorkoutById(id: String): WorkoutDetailResponse? {
        return try {
            client.get("${ApiRoutes.BASE_URL}${ApiRoutes.Workouts.byId(id)}").body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /* override fun editWorkout(workout: WorkoutExerciseData) {

    } */

    /* override fun createWorkout(workout: WorkoutExerciseData) {

    } */
}