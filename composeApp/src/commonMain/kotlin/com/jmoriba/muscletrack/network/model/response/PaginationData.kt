package com.jmoriba.muscletrack.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PaginationData(
    val page: Int,
    val limit: Int,
    val total: Int,
    val pages: Int
)