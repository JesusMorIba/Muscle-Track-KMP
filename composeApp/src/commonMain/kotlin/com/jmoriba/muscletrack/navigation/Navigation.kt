package com.jmoriba.muscletrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.jmoriba.muscletrack.feature.detail.presentation.WorkoutDetailViewModel
import com.jmoriba.muscletrack.feature.detail.ui.WorkoutDetailScreen
import com.jmoriba.muscletrack.feature.home.presentation.HomeViewModel
import com.jmoriba.muscletrack.feature.home.ui.HomeScreen
import com.jmoriba.muscletrack.feature.login.presentation.LoginViewModel
import com.jmoriba.muscletrack.feature.login.ui.LoginScreen
import com.jmoriba.muscletrack.feature.posedetection.presentation.PoseDetectionViewModel
import com.jmoriba.muscletrack.feature.posedetection.ui.PoseDetectionScreen
import com.jmoriba.muscletrack.feature.report.presentation.ReportViewModel
import com.jmoriba.muscletrack.feature.report.ui.ReportScreen
import com.jmoriba.muscletrack.feature.setting.ui.SettingScreen
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.transition.NavTransition
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation(navigator: Navigator) {

    NavHost(
        navigator = navigator,
        initialRoute = Routes.HOME,
        navTransition = NavTransition()
    ) {
        scene(route = Routes.LOGIN) {

            val viewModel = koinViewModel(LoginViewModel::class) { parametersOf() }

            LoginScreen(
                viewModel = viewModel,
                onWorkoutClick = { workout ->
                    navigator.navigate("/detail/${workout.id}")
                }
            )
        }

        scene(route = Routes.HOME) {
            val viewModel = koinViewModel(HomeViewModel::class) { parametersOf() }

            HomeScreen(
                viewModel = viewModel
            )
        }

        scene(route = Routes.REPORT) {
            val viewModel = koinViewModel(ReportViewModel::class) { parametersOf() }

            ReportScreen(
                viewModel = viewModel
            )
        }

        scene(route = Routes.DETECTION) {

            val viewModel = koinViewModel(PoseDetectionViewModel::class) { parametersOf() }

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            PoseDetectionScreen(
                uiState = uiState,
                onWorkoutClick = { workout ->
                    navigator.navigate("/detail/${workout.id}")
                }
            )
        }

        scene(route = Routes.SETTINGS) {
            SettingScreen()
        }

        scene(route = Routes.DETAIL) { backStackEntry ->
            val workoutId = backStackEntry.path<Int>("id")

            if (workoutId != null) {
                val viewModel = viewModel(modelClass = WorkoutDetailViewModel::class) {
                    WorkoutDetailViewModel()
                }

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                WorkoutDetailScreen(
                    uiState = uiState,
                    workoutId = workoutId
                )
            }
        }
    }
}
