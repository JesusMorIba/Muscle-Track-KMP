package com.jmoriba.muscletrack.feature.report.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmoriba.muscletrack.designsystem.component.calendar.Calendar
import com.jmoriba.muscletrack.designsystem.component.card.WorkoutHistoryCard
import com.jmoriba.muscletrack.designsystem.component.navigation.NavigationBar
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.feature.report.presentation.ReportUiState
import com.jmoriba.muscletrack.model.WorkoutModelUI
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReportScreen(uiState: ReportUiState, onWorkoutClick: (workout: WorkoutModelUI) -> Unit) {
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
                Calendar()
                WorkoutHistoryCard(
                    workouts = uiState.workouts,
                    onWorkoutClick = onWorkoutClick
                )
            }
        }
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    ReportScreen(
        uiState = ReportUiState(),
        onWorkoutClick = { }
    )
}
