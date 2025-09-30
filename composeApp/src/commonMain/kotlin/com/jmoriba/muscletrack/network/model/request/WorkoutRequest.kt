package com.jmoriba.muscletrack.network.model.request

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutSet(
    val weight: Int,
    val reps: Int,
    val pr: Boolean = false
)

@Serializable
data class WorkoutExercise(
    val exerciseId: String,
    val exerciseName: String,
    val position: Int,
    val sets: List<WorkoutSet> = emptyList()
)

@Serializable
data class Workout(
    val id: String,
    val name: String,
    val duration: Int? = null,
    val notes: String = "",
    val date: LocalDate,
    val isCompleted: Boolean = false,
    val exercises: List<WorkoutExercise> = emptyList(),
    val tags: List<String> = emptyList()
)

@Serializable
data class CreateWorkoutRequest(
    val name: String,
    val duration: Int,
    val notes: String? = null,
    val date: LocalDate,
    val exercises: List<WorkoutExercise> = emptyList(),
    val tags: List<Int> = emptyList()
)

@Serializable
data class UpdateWorkoutRequest(
    val name: String? = null,
    val duration: Int? = null,
    val notes: String? = null,
    val date: LocalDate? = null,
    val isCompleted: Boolean? = null
)