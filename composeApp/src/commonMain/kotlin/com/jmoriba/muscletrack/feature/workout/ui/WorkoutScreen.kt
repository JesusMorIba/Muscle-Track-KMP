package com.jmoriba.muscletrack.feature.workout.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.designsystem.component.card.WorkoutCard
import com.jmoriba.muscletrack.designsystem.component.searchbar.SearchBar
import com.jmoriba.muscletrack.designsystem.component.state.ErrorMessage
import com.jmoriba.muscletrack.designsystem.component.state.LoadingIndicator
import com.jmoriba.muscletrack.designsystem.component.tab.StatusTabs
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.di.previewModule
import com.jmoriba.muscletrack.feature.workout.presentation.WorkoutEvent
import com.jmoriba.muscletrack.feature.workout.presentation.WorkoutViewModel
import com.jmoriba.muscletrack.network.model.entities.WorkoutData
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkoutScreen(viewModel : WorkoutViewModel, onWorkoutClick: (String) -> Unit) {
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
                SearchBar("",{})

                Spacer(modifier = Modifier.height(spacingS()))

                StatusTabs(
                    selectedFilter = uiState.selectedFilter,
                    onFilterChange = { newFilter ->
                        viewModel.handle(WorkoutEvent.FilterChanged(newFilter))
                    }
                )

                Spacer(modifier = Modifier.height(spacingS()))

                when (val workouts = uiState.filteredWorkouts) {
                    is Resource.Loading -> {
                        LoadingIndicator()
                    }

                    is Resource.Success -> {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            workouts.data.forEach { workout ->
                                WorkoutCard(
                                    workout = workout,
                                    onWorkoutClick = { onWorkoutClick(workout.id) }
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        ErrorMessage("Error loading workouts: ${workouts.error}")
                    }
                }

                Spacer(modifier = Modifier.height(spacingS()))
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
            val viewModel = koinViewModel<WorkoutViewModel>()
            WorkoutScreen(viewModel = viewModel) {}
        }
    }
}
