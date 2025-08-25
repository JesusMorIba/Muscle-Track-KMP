package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class PaginationData(
    val page: Int,
    val limit: Int,
    val total: Int,
    val pages: Int
)