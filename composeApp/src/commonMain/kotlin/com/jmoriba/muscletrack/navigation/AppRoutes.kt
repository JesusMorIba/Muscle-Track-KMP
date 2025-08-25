package com.jmoriba.muscletrack.navigation

import moe.tlaster.precompose.navigation.Navigator
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_dashboard
import muscletrack.composeapp.generated.resources.ic_pose
import muscletrack.composeapp.generated.resources.ic_workout
import muscletrack.composeapp.generated.resources.ic_setting
import muscletrack.composeapp.generated.resources.ic_exercise
import org.jetbrains.compose.resources.DrawableResource

sealed class AppRoutes(
    val route: String,
    val title: String? = null,
    val icon: DrawableResource? = null,
    val showBottomBar: Boolean = true,
    val showBackButton: Boolean = false,
    val fabAction: ((Navigator) -> Unit)? = null
) {
    object Login : AppRoutes("login", showBottomBar = false)
    object Signup : AppRoutes("signup", showBottomBar = false)
    object Dashboard : AppRoutes("dashboard", title = "Dashboard", icon = Res.drawable.ic_dashboard)
    object Workouts : AppRoutes("workouts", title = "Workouts", icon = Res.drawable.ic_workout, fabAction = { nav -> nav.navigate("workout_create") })
    object Exercises : AppRoutes("exercises", title = "Exercises", icon = Res.drawable.ic_exercise, fabAction = { nav -> nav.navigate("exercise_create") })
    object Detection : AppRoutes("detection", title = "Pose Detection", icon = Res.drawable.ic_pose)
    object Settings : AppRoutes("settings", title = "Settings", icon = Res.drawable.ic_setting)
    object WorkoutDetail : AppRoutes(route = "workout_detail/{id}", title = "Workout Detail", showBottomBar = false, showBackButton = true)
    object ExerciseDetail : AppRoutes(route = "exercise_detail/{id}", title = "Exercise Detail", showBottomBar = false, showBackButton = true)

    companion object {
        val allRoutes = listOf(Login, Signup, Dashboard, Workouts, Exercises, Detection, Settings, WorkoutDetail, ExerciseDetail)

        val bottomNavItems = listOf(Dashboard, Workouts, Exercises, Settings)

        fun fromRoute(route: String?): AppRoutes? {
            return when {
                route == null -> null
                route.startsWith(WorkoutDetail.route.substringBefore("/{")) -> WorkoutDetail
                route.startsWith(ExerciseDetail.route.substringBefore("/{")) -> ExerciseDetail
                else -> allRoutes.find { it.route == route }
            }
        }
    }
}