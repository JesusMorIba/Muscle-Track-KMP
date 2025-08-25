package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MuscleEnum {
    @SerialName("CHEST")
    CHEST,
    @SerialName("BACK")
    BACK,
    @SerialName("SHOULDERS")
    SHOULDERS,
    @SerialName("BICEPS")
    BICEPS,
    @SerialName("TRICEPS")
    TRICEPS,
    @SerialName("FOREARMS")
    FOREARMS,
    @SerialName("ABS")
    ABS,
    @SerialName("QUADS")
    QUADS,
    @SerialName("HAMSTRINGS")
    HAMSTRINGS,
    @SerialName("CALVES")
    CALVES,
    @SerialName("GLUTES")
    GLUTES
}

fun MuscleEnum.getDisplayName(): String {
    return when (this) {
        MuscleEnum.CHEST -> "Chest"
        MuscleEnum.BACK -> "Back"
        MuscleEnum.SHOULDERS -> "Shoulders"
        MuscleEnum.BICEPS -> "Biceps"
        MuscleEnum.TRICEPS -> "Triceps"
        MuscleEnum.FOREARMS -> "Forearms"
        MuscleEnum.ABS -> "Abdominal"
        MuscleEnum.QUADS -> "Quadriceps"
        MuscleEnum.HAMSTRINGS -> "Hamstrings"
        MuscleEnum.CALVES -> "Calves"
        MuscleEnum.GLUTES -> "Glutes"
    }
}