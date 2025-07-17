package com.jmoriba.muscletrack.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class EquipmentResponse(
    val id: Int,
    val name: String
)
