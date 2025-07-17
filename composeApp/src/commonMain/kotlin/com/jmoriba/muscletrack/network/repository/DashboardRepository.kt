package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.network.model.response.DashboardData

interface DashboardRepository {

    suspend fun getDashboardData(): DashboardData
}