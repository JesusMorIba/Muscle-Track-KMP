package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutExerciseData(
    val id: String,
    val workoutId: String,
    val exerciseId: String,
    val position: Int,
    val exercise: ExerciseData,
    val exerciseSets: List<ExerciseSet>
)