package com.jmoriba.muscletrack.feature.workoutdetail.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmoriba.muscletrack.common.utils.Resource
import com.jmoriba.muscletrack.designsystem.component.button.ActionButton
import com.jmoriba.muscletrack.designsystem.component.card.ExerciseSetCard
import com.jmoriba.muscletrack.designsystem.component.card.NotesCard
import com.jmoriba.muscletrack.designsystem.component.state.ErrorMessage
import com.jmoriba.muscletrack.designsystem.component.state.LoadingIndicator
import com.jmoriba.muscletrack.designsystem.component.tag.WorkoutTag
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.di.previewModule
import com.jmoriba.muscletrack.feature.workoutdetail.presentation.WorkoutDetailViewModel
import com.jmoriba.muscletrack.network.model.entities.ExerciseSet
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkoutDetailScreen(
    viewModel: WorkoutDetailViewModel,
    workoutId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val workout = uiState.workout

    val exerciseSets = remember {
        listOf(
            ExerciseSet(1, "Normal", "80 kg", 8, true),
            ExerciseSet(2, "Normal", "85 kg", 6, true),
            ExerciseSet(3, "Normal", "90 kg", 4, true)
        )
    }

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
                when (val workout = uiState.workout) {
                    is Resource.Loading -> {
                        LoadingIndicator()
                    }
                    is Resource.Success -> {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        Icons.Default.DateRange,
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "June 15, 2023",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Schedule,
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "65 min",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }

                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0xFF4CAF50).copy(alpha = 0.1f)
                                ) {
                                    Text(
                                        text = "Completed",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                        fontSize = 12.sp,
                                        color = Color(0xFF4CAF50),
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                            Row() {
                                ActionButton(
                                    icon = Icons.Default.Close,
                                    text = "Mark Incomplete",
                                    backgroundColor = Color(0xFFFFF3CD),
                                    iconTint = Color(0xFF856404),
                                    onClick = { /* TODO */ }
                                )

                                ActionButton(
                                    icon = Icons.Default.Edit,
                                    text = "Edit",
                                    backgroundColor = Color(0xFFE2E3F3),
                                    iconTint = Color(0xFF383D7C),
                                    onClick = { /* TODO */ }
                                )

                                ActionButton(
                                    icon = Icons.Outlined.Delete,
                                    backgroundColor = Color.Transparent,
                                    iconTint = Color(0xFFFF0050),
                                    onClick = { /* TODO */ }
                                )
                            }

                            NotesCard(workout.data.notes ?: "")

                            Row() {
                                WorkoutTag("Strength", Color(0xFF8B5CF6))
                                WorkoutTag("Upper Body", Color(0xFF8B5CF6))
                                WorkoutTag("Heavy", Color(0xFF8B5CF6))
                            }

                            ExerciseSetCard(exerciseSets)
                        }
                    }

                    is Resource.Error -> {
                        ErrorMessage("Error loading workouts: ${workout.error}")
                    }
                }

                Spacer(modifier = Modifier.height(spacingS()))
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
            val viewModel = koinViewModel<WorkoutDetailViewModel>()
            WorkoutDetailScreen(viewModel = viewModel, workoutId = "2")
        }
    }
}
