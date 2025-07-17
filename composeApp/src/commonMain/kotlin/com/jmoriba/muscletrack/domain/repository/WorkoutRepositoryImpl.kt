package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.network.repository.WorkoutRepository
import com.jmoriba.muscletrack.network.api.ApiRoutes
import com.jmoriba.muscletrack.network.api.HttpClientProvider
import com.jmoriba.muscletrack.network.model.response.WorkoutData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class WorkoutRepositoryImpl(clientProvider: HttpClientProvider) : WorkoutRepository {

    private val client: HttpClient = clientProvider.privateClient

    override suspend fun getWorkouts(): List<WorkoutData> {
        return try {
            client.get {
                url("${ApiRoutes.BASE_URL}${ApiRoutes.Workouts.All}")
            }.body()
        } catch (e: Exception) {
            throw RuntimeException("Error al obtener los datos de workouts", e)
        }
    }

    /* override fun editWorkout(workout: WorkoutExerciseData) {

    } */

    /* override fun createWorkout(workout: WorkoutExerciseData) {

    } */
}