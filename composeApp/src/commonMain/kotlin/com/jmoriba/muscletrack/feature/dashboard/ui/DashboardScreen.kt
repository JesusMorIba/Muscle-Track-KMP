package com.jmoriba.muscletrack.feature.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.designsystem.component.card.DashboardStatsCard
import com.jmoriba.muscletrack.designsystem.component.card.MuscleGroupBalanceCard
import com.jmoriba.muscletrack.designsystem.component.card.RecentWorkoutsCard
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.di.previewModule
import com.jmoriba.muscletrack.feature.dashboard.presentation.DashboardViewModel
import com.jmoriba.muscletrack.network.model.entities.getDisplayName
import com.jmoriba.muscletrack.network.model.response.RecentWorkout
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
fun DashboardScreen(viewModel : DashboardViewModel, onWorkoutClick: (RecentWorkout) -> Unit, onViewAllWorkouts:() -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(Modifier.background(LightBackgroundAppColor)) {
        Scaffold(
            containerColor = Color.Transparent
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .imePadding()
                    .padding(horizontal = spacingS())
            ) {

                when (val data = uiState.dashboardData) {
                    is Resource.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Resource.Success -> {
                        val dashboard = data.data

                        DashboardStatsCard(dashboard)

                        Spacer(modifier = Modifier.height(spacingS()))

                        MuscleGroupBalanceCard(
                            radarLabels = dashboard.muscleStats.map { it.muscleName.getDisplayName() },
                            muscleValues = dashboard.muscleStats.map { it.totalReps.toDouble() }
                        )

                        Spacer(modifier = Modifier.height(spacingS()))

                        RecentWorkoutsCard(
                            workouts = dashboard.recentWorkouts,
                            onWorkoutClick = onWorkoutClick,
                            onViewAllWorkouts = onViewAllWorkouts
                        )

                        Spacer(modifier = Modifier.height(spacingS()))
                    }
                    is Resource.Error -> {
                        Column {
                            Text("Error: ${data.error}")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    PreComposeApp {
        KoinApplication(application = {
            modules(previewModule())
        }) {
            val viewModel = koinViewModel<DashboardViewModel>()
            DashboardScreen(viewModel = viewModel, {}, {})
        }
    }
}