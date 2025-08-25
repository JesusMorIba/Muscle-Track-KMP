package com.jmoriba.muscletrack.network.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TagEnum {
    @SerialName("STRENGTH")
    STRENGTH,

    @SerialName("UPPER_BODY")
    UPPER_BODY,

    @SerialName("HEAVY")
    HEAVY,

    @SerialName("LOWER_BODY")
    LOWER_BODY,

    @SerialName("CARDIO")
    CARDIO,

    @SerialName("HIIT")
    HIIT,

    @SerialName("ENDURANCE")
    ENDURANCE,

    @SerialName("PUSH")
    PUSH,

    @SerialName("PULL")
    PULL
}

fun TagEnum.getDisplayName(): String {
    return when (this) {
        TagEnum.STRENGTH -> "Fuerza"
        TagEnum.UPPER_BODY -> "Tren superior"
        TagEnum.HEAVY -> "Pesado"
        TagEnum.LOWER_BODY -> "Tren inferior"
        TagEnum.CARDIO -> "Cardio"
        TagEnum.HIIT -> "HIIT"
        TagEnum.ENDURANCE -> "Resistencia"
        TagEnum.PUSH -> "Empuje"
        TagEnum.PULL -> "Jalón"
    }
}
