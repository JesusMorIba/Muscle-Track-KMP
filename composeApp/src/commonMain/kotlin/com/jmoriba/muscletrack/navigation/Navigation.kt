package com.jmoriba.muscletrack.navigation

import androidx.compose.runtime.Composable
import com.jmoriba.muscletrack.feature.auth.presentation.AuthViewModel
import com.jmoriba.muscletrack.feature.dashboard.presentation.DashboardViewModel
import com.jmoriba.muscletrack.feature.dashboard.ui.DashboardScreen
import com.jmoriba.muscletrack.feature.exercise.presentation.ExerciseViewModel
import com.jmoriba.muscletrack.feature.exercise.ui.ExerciseScreen
import com.jmoriba.muscletrack.feature.exercisedetail.presentation.ExerciseDetailViewModel
import com.jmoriba.muscletrack.feature.exercisedetail.ui.ExerciseDetailScreen
import com.jmoriba.muscletrack.feature.auth.ui.LoginScreen
import com.jmoriba.muscletrack.feature.posedetection.presentation.PoseDetectionViewModel
import com.jmoriba.muscletrack.feature.posedetection.ui.PoseDetectionScreen
import com.jmoriba.muscletrack.feature.workout.presentation.WorkoutViewModel
import com.jmoriba.muscletrack.feature.workout.ui.WorkoutScreen
import com.jmoriba.muscletrack.feature.setting.ui.SettingScreen
import com.jmoriba.muscletrack.feature.workoutdetail.presentation.WorkoutDetailViewModel
import com.jmoriba.muscletrack.feature.workoutdetail.ui.WorkoutDetailScreen
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation(navigator: Navigator) {

    NavHost(
        navigator = navigator,
        initialRoute = AppRoutes.Dashboard.route,
        navTransition = NavTransition()
    ) {
        scene(route = AppRoutes.Login.route) {

            val viewModel = koinViewModel(AuthViewModel::class) { parametersOf() }

            LoginScreen(
                onNavigateToRegister = { navigator.navigate("signup") },
                onLoginSuccess = { navigator.navigate("dashboard") },
                viewModel = viewModel,
            )
        }

        scene(route = AppRoutes.Dashboard.route) {
            val viewModel = koinViewModel(DashboardViewModel::class) { parametersOf() }

            DashboardScreen(
                viewModel = viewModel,
                onWorkoutClick = { id ->
                    navigator.navigate("detail/${id}")
                },
                onViewAllWorkouts = {
                    navigator.navigate(AppRoutes.Workouts.route)
                }
            )
        }

        scene(route = AppRoutes.Workouts.route) {
            val viewModel = koinViewModel(WorkoutViewModel::class) { parametersOf() }

            WorkoutScreen(
                viewModel = viewModel,
                onWorkoutClick = { id ->
                    navigator.navigate("workout_detail/${id}")
                }
            )
        }

        scene(route = AppRoutes.Exercises.route) {
            val viewModel = koinViewModel(ExerciseViewModel::class) { parametersOf() }

            ExerciseScreen(
                viewModel = viewModel,
                onExerciseClick = { exerciseId ->
                    navigator.navigate("exercise_detail/$exerciseId")
                }
            )
        }

        scene(route = AppRoutes.Detection.route) {

            val viewModel = koinViewModel(PoseDetectionViewModel::class) { parametersOf() }

            PoseDetectionScreen(
                viewModel = viewModel,
            )
        }

        scene(route = AppRoutes.Settings.route) {
            SettingScreen()
        }

        scene(route = AppRoutes.WorkoutDetail.route) { backStackEntry ->
            val workoutId = backStackEntry.path<String>("id")

            if (workoutId != null) {
                val viewModel = koinViewModel(WorkoutDetailViewModel::class) { parametersOf() }

                WorkoutDetailScreen(
                    viewModel = viewModel,
                    workoutId = workoutId
                )
            }
        }

        scene(route = AppRoutes.ExerciseDetail.route) { backStackEntry ->
            val exerciseId = backStackEntry.path<String>("id")

            if (exerciseId != null) {
                val viewModel = koinViewModel(ExerciseDetailViewModel::class) { parametersOf() }

                ExerciseDetailScreen(
                    viewModel = viewModel,
                    exerciseId = exerciseId
                )
            }
        }
    }
}
