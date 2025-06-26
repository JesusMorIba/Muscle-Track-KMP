package com.jmoriba.muscletrack.domain.models

import com.jmoriba.muscletrack.data.models.response.WorkoutData

data class WorkoutModelUI(
    val id: String,
    val name: String,
    val isCompleted: Boolean,
    val duration: Int,
    val notes: String?,
    val date: String,
    val tags: List<String> = emptyList()
) {
    companion object {
        fun defaultWorkoutModelUI(): WorkoutModelUI {
            return WorkoutModelUI(
                id = "1",
                name = "Default Workout",
                isCompleted = false,
                duration = 15,
                notes = "Workout",
                date = "Lun",
                tags = listOf("default")
            )
        }

        fun defaultWorkoutModelUIList(): List<WorkoutModelUI> {
            return listOf(
                WorkoutModelUI(
                    id = "1",
                    name = "Full Body Strength",
                    isCompleted = true,
                    duration = 40,
                    notes = "Workout",
                    date = "Lun",
                    tags = listOf("fuerza", "fullbody")
                ),
                WorkoutModelUI(
                    id = "2",
                    name = "Upper Body Blast",
                    isCompleted = false,
                    duration = 35,
                    notes = "Workout",
                    date = "Mar",
                    tags = listOf("superior")
                ),
                WorkoutModelUI(
                    id = "3",
                    name = "Cardio Burn",
                    isCompleted = true,
                    duration = 30,
                    notes = "Workout",
                    date = "Mié",
                    tags = listOf("cardio")
                ),
                WorkoutModelUI(
                    id = "4",
                    name = "Leg Day Extreme",
                    isCompleted = false,
                    duration = 45,
                    notes = "Workout",
                    date = "Jue",
                    tags = listOf("piernas")
                ),
                WorkoutModelUI(
                    id = "5",
                    name = "Core & Stretch",
                    isCompleted = true,
                    duration = 25,
                    notes = "Workout",
                    date = "Vie",
                    tags = listOf("core", "estiramiento")
                )
            )
        }

        fun WorkoutData.toWorkoutModelUI(): WorkoutModelUI {
            return WorkoutModelUI(
                id = this.id,
                name = this.name,
                isCompleted = this.isCompleted,
                duration = this.duration,
                notes = this.notes,
                date = this.date
            )
        }
    }
}
