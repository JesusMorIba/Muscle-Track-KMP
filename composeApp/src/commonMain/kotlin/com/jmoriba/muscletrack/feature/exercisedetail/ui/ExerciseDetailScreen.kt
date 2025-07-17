package com.jmoriba.muscletrack.feature.exercisedetail.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmoriba.muscletrack.designsystem.component.card.ExerciseProgressCard
import com.jmoriba.muscletrack.designsystem.component.card.ExerciseDetailCard
import com.jmoriba.muscletrack.designsystem.component.card.ExerciseWorkoutHistoryCard
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.di.previewModule
import com.jmoriba.muscletrack.feature.exercisedetail.presentation.ExerciseDetailEvent
import com.jmoriba.muscletrack.feature.exercisedetail.presentation.ExerciseDetailViewModel
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExerciseDetailScreen(
    viewModel: ExerciseDetailViewModel,
    exerciseId: String
) {
    LaunchedEffect(exerciseId) {
        viewModel.handle(ExerciseDetailEvent.GetExerciseById(exerciseId))
    }

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

                /*

                ExerciseProgressCard(exerciseData = uiState.selectedExercise)

                Spacer(modifier = Modifier.height(spacingS()))

                ExerciseDetailCard(exercise = uiState.selectedExercise)

                Spacer(modifier = Modifier.height(spacingS()))

                ExerciseWorkoutHistoryCard(workouts = uiState.workoutHistory.filterNotNull())

                Spacer(modifier = Modifier.height(spacingS()))

                 */
            }
        }
    }
}

@Preview
@Composable
fun WorkoutDetailScreenPreview() {
    PreComposeApp {
        KoinApplication(application = {
            modules(previewModule())
        }) {
            val viewModel = koinViewModel<ExerciseDetailViewModel>()
            ExerciseDetailScreen(viewModel = viewModel, exerciseId = "2")
        }
    }
}
