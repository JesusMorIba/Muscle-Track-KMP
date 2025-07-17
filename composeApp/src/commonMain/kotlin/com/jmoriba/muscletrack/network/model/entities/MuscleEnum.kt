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
        MuscleEnum.CHEST -> "Pecho"
        MuscleEnum.BACK -> "Espalda"
        MuscleEnum.SHOULDERS -> "Hombros"
        MuscleEnum.BICEPS -> "Bíceps"
        MuscleEnum.TRICEPS -> "Tríceps"
        MuscleEnum.FOREARMS -> "Antebrazos"
        MuscleEnum.ABS -> "Abdominales"
        MuscleEnum.QUADS -> "Cuádriceps"
        MuscleEnum.HAMSTRINGS -> "Isquiotibiales"
        MuscleEnum.CALVES -> "Pantorrillas"
        MuscleEnum.GLUTES -> "Glúteos"
    }
}