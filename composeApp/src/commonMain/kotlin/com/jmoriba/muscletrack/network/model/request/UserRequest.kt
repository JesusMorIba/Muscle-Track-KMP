package com.jmoriba.muscletrack.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val email: String,
    val password: String,
    val firstName: String? = null,
    val lastName: String? = null
)