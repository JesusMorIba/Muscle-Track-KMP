package com.jmoriba.muscletrack.domain.models

import com.jmoriba.muscletrack.data.local.models.WorkoutData

data class WorkoutModelUI(
    val id: String,
    val name: String,
    val photo: String?,
    val kcal: Int?,
    val duration: Int,
    val date: String,
) {
    companion object {
        fun defaultWorkoutModelUI(): WorkoutModelUI {
            return WorkoutModelUI(
                id = "1",
                name = "Default Workout",
                photo = "https://i.imgur.com/N1GXnzq.png",
                kcal = 4,
                duration = 15,
                date = "Lun"
            )
        }

        fun defaultWorkoutModelUIList(): List<WorkoutModelUI> {
            return listOf(
                WorkoutModelUI(
                    id = "1",
                    name = "Full Body Strength",
                    photo = "https://i.imgur.com/N1GXnzq.png",
                    kcal = 150,
                    duration = 40,
                    date = "Lun"
                ),
                WorkoutModelUI(
                    id = "2",
                    name = "Upper Body Blast",
                    photo = "https://i.imgur.com/N1GXnzq.png",
                    kcal = 120,
                    duration = 35,
                    date = "Mar"
                ),
                WorkoutModelUI(
                    id = "3",
                    name = "Cardio Burn",
                    photo = "https://i.imgur.com/N1GXnzq.png",
                    kcal = 200,
                    duration = 30,
                    date = "Mié"
                ),
                WorkoutModelUI(
                    id = "4",
                    name = "Leg Day Extreme",
                    photo = "https://i.imgur.com/N1GXnzq.png",
                    kcal = 180,
                    duration = 45,
                    date = "Jue"
                ),
                WorkoutModelUI(
                    id = "5",
                    name = "Core & Stretch",
                    photo = "https://i.imgur.com/N1GXnzq.png",
                    kcal = 100,
                    duration = 25,
                    date = "Vie"
                )
            )
        }

        fun WorkoutData.toWorkoutModelUI(): WorkoutModelUI {
            return WorkoutModelUI(
                id = this.id,
                name = this.name,
                photo = this.photo,
                kcal = this.kcal,
                duration = this.duration,
                date = this.date
            )
        }
    }
}
