package com.jmoriba.muscletrack.data.models.entities

enum class Equipment(val displayName: String) {
    DUMBBELL("Dumbbell"),
    BARBELL("Barbell"),
    MACHINE("Machine"),
    BODYWEIGHT("Bodyweight"),
    KETTLEBELL("Kettlebell");

    override fun toString(): String = displayName
}