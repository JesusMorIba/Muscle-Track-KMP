package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.SerialName

enum class EquipmentEnum {
    @SerialName("DUMBBELL")
    DUMBBELL,

    @SerialName("BARBELL")
    BARBELL,

    @SerialName("MACHINE")
    MACHINE,

    @SerialName("BODYWEIGHT")
    BODYWEIGHT,

    @SerialName("KETTLEBELL")
    KETTLEBELL
}

fun EquipmentEnum.getDisplayName(): String {
    return when (this) {
        EquipmentEnum.DUMBBELL -> "Pecho"
        EquipmentEnum.BARBELL -> "Espalda"
        EquipmentEnum.MACHINE -> "Hombros"
        EquipmentEnum.BODYWEIGHT -> "Bíceps"
        EquipmentEnum.KETTLEBELL -> "Tríceps"
    }
}
