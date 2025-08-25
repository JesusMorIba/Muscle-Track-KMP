package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class RecentWorkoutData(
    val id: String,
    val name: String,
    val date: String,
    val status: String
)