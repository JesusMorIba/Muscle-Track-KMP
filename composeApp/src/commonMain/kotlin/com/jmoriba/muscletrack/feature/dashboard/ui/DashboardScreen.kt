package com.jmoriba.muscletrack.feature.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
fun DashboardScreen(viewModel : DashboardViewModel, onWorkoutClick: (String) -> Unit, onViewAllWorkouts:() -> Unit) {
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

                when (val state = uiState.dashboardData) {
                    is Resource.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is Resource.Success -> {
                        val dashboard = state.data

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(spacingS())
                        ) {
                            dashboard.stats?.let { DashboardStatsCard(it) }

                            /*
                            MuscleGroupBalanceCard(
                                radarLabels = dashboard.muscleStats?.map { it.muscleName.getDisplayName() }.orEmpty(),
                                muscleValues = dashboard.muscleStats?.map { it.totalReps.toDouble() }.orEmpty()
                            )*/

                            RecentWorkoutsCard(
                                workouts = dashboard.recentWorkouts.orEmpty(),
                                onWorkoutClick = onWorkoutClick,
                                onViewAllWorkouts = onViewAllWorkouts
                            )
                        }
                    }

                    is Resource.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error: ${state.error}",
                                color = MaterialTheme.colorScheme.error
                            )
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