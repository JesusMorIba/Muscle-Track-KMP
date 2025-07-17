package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.network.api.ApiRoutes
import com.jmoriba.muscletrack.network.api.HttpClientProvider
import com.jmoriba.muscletrack.network.model.response.DashboardData
import com.jmoriba.muscletrack.network.repository.DashboardRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class DashboardRepositoryImpl(
    clientProvider: HttpClientProvider
) : DashboardRepository {

    private val client = clientProvider.privateClient

    override suspend fun getDashboardData(): DashboardData {
        return try {
            client.get {
                url("${ApiRoutes.BASE_URL}${ApiRoutes.Dashboard.Stats}")
            }.body()
        } catch (e: Exception) {
            throw RuntimeException("Error al obtener los datos del dashboard", e)
        }
    }
}
