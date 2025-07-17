package com.jmoriba.muscletrack.network.repository

import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.network.model.response.DashboardResponse

interface DashboardRepository {

    suspend fun getDashboardData(): Resource<DashboardResponse?>
}