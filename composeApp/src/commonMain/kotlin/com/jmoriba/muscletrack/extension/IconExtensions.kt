package com.jmoriba.muscletrack.extension

import org.jetbrains.compose.resources.DrawableResource
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_time

fun iconNameToResource(name: String): DrawableResource = when (name) {
    "add" -> Res.drawable.ic_time
    "settings" -> Res.drawable.ic_time
    else -> Res.drawable.ic_time
}
