package com.jmoriba.muscletrack.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class TagRequest(
    val name: String
)