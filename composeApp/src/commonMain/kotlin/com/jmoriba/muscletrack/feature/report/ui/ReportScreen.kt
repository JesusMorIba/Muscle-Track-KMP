package com.jmoriba.muscletrack.feature.report.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmoriba.muscletrack.designsystem.component.calendar.Calendar
import com.jmoriba.muscletrack.designsystem.component.card.WorkoutReportCard
import com.jmoriba.muscletrack.designsystem.component.title.ScreenTitle
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.di.previewModule
import com.jmoriba.muscletrack.feature.report.presentation.ReportEvent
import com.jmoriba.muscletrack.feature.report.presentation.ReportViewModel
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReportScreen(viewModel : ReportViewModel) {
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
                ScreenTitle("Report")
                Calendar(
                    selectedDate = uiState.selectedDate,
                    onDateSelected = { viewModel.handle(ReportEvent.DayChanged(it)) }
                )
                WorkoutReportCard(
                    workouts = uiState.workouts,
                    onWorkoutClick = { workout -> viewModel.handle(ReportEvent.WorkoutClicked(workout)) }
                )
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
            val viewModel = koinViewModel<ReportViewModel>()
            ReportScreen(viewModel = viewModel)
        }
    }
}
