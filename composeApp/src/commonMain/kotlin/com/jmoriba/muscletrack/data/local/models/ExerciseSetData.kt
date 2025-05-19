package com.jmoriba.muscletrack.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSetData(
    val weight: Int,
    val reps: Int,
    val rir: Int
) {
    companion object {
        fun defaultExerciseSet(): ExerciseSetData {
            return ExerciseSetData(
                weight = 120,
                reps = 10,
                rir = 10
            )
        }
    }
}