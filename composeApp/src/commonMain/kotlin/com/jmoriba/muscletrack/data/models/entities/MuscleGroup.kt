package com.jmoriba.muscletrack.data.models.entities

enum class MuscleGroup(val displayName: String) {
    CHEST("Chest"),
    BACK("Back"),
    LEGS("Legs"),
    SHOULDERS("Shoulders"),
    ARMS("Arms"),
    CORE("Core");

    override fun toString(): String = displayName
}