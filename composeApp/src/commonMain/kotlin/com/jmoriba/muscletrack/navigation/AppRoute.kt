package com.jmoriba.muscletrack.navigation

import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_dashboard
import muscletrack.composeapp.generated.resources.ic_pose
import muscletrack.composeapp.generated.resources.ic_workout
import muscletrack.composeapp.generated.resources.ic_setting
import muscletrack.composeapp.generated.resources.ic_exercise
import org.jetbrains.compose.resources.DrawableResource

sealed class AppRoute(
    val route: String,
    val title: String? = null,
    val icon: DrawableResource? = null,
    val showBottomBar: Boolean = true,
    val showBackButton: Boolean = false,
    val showFloatingButton: Boolean = false
) {
    object Login : AppRoute("login", showBottomBar = false)
    object Signup : AppRoute("signup", showBottomBar = false)
    object Dashboard : AppRoute("dashboard", title = "Dashboard", icon = Res.drawable.ic_dashboard)
    object Workouts : AppRoute("workouts", title = "Workouts", icon = Res.drawable.ic_workout, showFloatingButton = true)
    object Exercises : AppRoute("exercises", title = "Exercises", icon = Res.drawable.ic_exercise)
    object Detection : AppRoute("detection", title = "Pose Detection", icon = Res.drawable.ic_pose)
    object Settings : AppRoute("settings", title = "Settings", icon = Res.drawable.ic_setting)
    object WorkoutDetail : AppRoute(route = "workout_detail/{id}", title = "Workout Detail", showBottomBar = false, showBackButton = true)
    object ExerciseDetail : AppRoute(route = "exercise_detail/{id}", title = "Exercise Detail", showBottomBar = false, showBackButton = true)

    companion object {
        val allRoutes = listOf(Login, Signup, Dashboard, Workouts, Exercises, Detection, Settings, WorkoutDetail, ExerciseDetail)

        val bottomNavItems = listOf(Dashboard, Workouts, Exercises, Settings)

        fun fromRoute(route: String?): AppRoute? {
            return when {
                route == null -> null
                route.startsWith(WorkoutDetail.route.substringBefore("/{")) -> WorkoutDetail
                route.startsWith(ExerciseDetail.route.substringBefore("/{")) -> ExerciseDetail
                else -> allRoutes.find { it.route == route }
            }
        }
    }
}