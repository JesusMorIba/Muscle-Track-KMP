package com.jmoriba.muscletrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.jmoriba.muscletrack.data.repository.HistoryRepository
import com.jmoriba.muscletrack.feature.detail.presentation.WorkoutDetailViewModel
import com.jmoriba.muscletrack.feature.detail.ui.WorkoutDetailScreen
import com.jmoriba.muscletrack.feature.home.ui.HomeScreen
import com.jmoriba.muscletrack.feature.posedetection.ui.PoseDetectionScreen
import com.jmoriba.muscletrack.feature.report.presentation.ReportViewModel
import com.jmoriba.muscletrack.feature.report.ui.ReportScreen
import com.jmoriba.muscletrack.feature.setting.ui.SettingScreen
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.transition.NavTransition
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun Navigation(navigator: Navigator) {

    NavHost(
        navigator = navigator,
        initialRoute = Routes.HOME,
        navTransition = NavTransition()
    ) {
        scene(route = Routes.HOME) {
            val viewModel = viewModel(modelClass = ReportViewModel::class) {
                ReportViewModel(HistoryRepository)
            }
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            HomeScreen()
        }

        scene(route = Routes.REPORT) {
            val viewModel = viewModel(modelClass = ReportViewModel::class) {
                ReportViewModel(HistoryRepository)
            }
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            ReportScreen(
                uiState = uiState,
                onWorkoutClick = { workout ->
                    navigator.navigate("/detail/${workout.id}")
                }
            )
        }

        scene(route = Routes.DETECTION) {
            val viewModel = viewModel(modelClass = ReportViewModel::class) {
                ReportViewModel(HistoryRepository)
            }
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            PoseDetectionScreen()
        }

        scene(route = Routes.SETTINGS) {
            val viewModel = viewModel(modelClass = ReportViewModel::class) {
                ReportViewModel(HistoryRepository)
            }
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            SettingScreen()
        }

        scene(route = Routes.DETAIL) { backStackEntry ->
            val workoutId = backStackEntry.path<Int>("id")

            if (workoutId != null) {
                val viewModel = viewModel(modelClass = WorkoutDetailViewModel::class) {
                    WorkoutDetailViewModel(HistoryRepository)
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
