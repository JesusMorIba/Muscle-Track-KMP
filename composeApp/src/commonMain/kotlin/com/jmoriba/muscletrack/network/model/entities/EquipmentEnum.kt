package com.jmoriba.muscletrack.network.model.entities

enum class EquipmentEnum(val displayName: String) {
    DUMBBELL("Dumbbell"),
    BARBELL("Barbell"),
    MACHINE("Machine"),
    BODYWEIGHT("Bodyweight"),
    KETTLEBELL("Kettlebell");

    override fun toString(): String = displayName
}