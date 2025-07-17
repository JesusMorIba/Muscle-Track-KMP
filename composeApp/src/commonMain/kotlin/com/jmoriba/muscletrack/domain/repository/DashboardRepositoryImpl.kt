package com.jmoriba.muscletrack.domain.repository

import com.jmoriba.muscletrack.common.utils.AppError
import com.jmoriba.muscletrack.common.utils.ErrorHandler
import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.network.api.ApiRoutes
import com.jmoriba.muscletrack.network.api.HttpClientProvider
import com.jmoriba.muscletrack.network.model.response.DashboardResponse
import com.jmoriba.muscletrack.network.repository.DashboardRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class DashboardRepositoryImpl(
    clientProvider: HttpClientProvider
) : DashboardRepository {

    private val client = clientProvider.privateClient

    override suspend fun getDashboardData(): Resource<DashboardResponse> {
        return try {
            val response: DashboardResponse = client.get {
                url("${ApiRoutes.BASE_URL}${ApiRoutes.Workouts.All}")
            }.body()
            Resource.Success(response)
        } catch (e: Exception) {
            val error = ErrorHandler.handleException(e)
            Resource.Error(error)
        }
    }
}
