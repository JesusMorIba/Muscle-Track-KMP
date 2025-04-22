package com.jmoriba.muscletrack.feature.detail.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmoriba.muscletrack.designsystem.component.navigation.NavigationBar
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.feature.detail.presentation.WorkoutDetailUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkoutDetailScreen(uiState: WorkoutDetailUiState, workoutId: Int) {
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
                Text(uiState.workout.toString())
            }
        }
    }
}

@Preview
@Composable
fun WorkoutDetailScreenPreview() {
    WorkoutDetailScreen(
        uiState = WorkoutDetailUiState(),
        workoutId = 0
    )
}
