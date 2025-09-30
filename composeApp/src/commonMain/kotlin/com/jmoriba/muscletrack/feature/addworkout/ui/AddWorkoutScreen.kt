package com.jmoriba.muscletrack.feature.addworkout.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.di.previewModule
import com.jmoriba.muscletrack.feature.addworkout.presentation.AddWorkoutViewModel
import com.jmoriba.muscletrack.network.model.entities.WorkoutExerciseData
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddWorkoutScreen(
    viewModel: AddWorkoutViewModel,
    onDismiss: () -> Unit = {},
    onSave: (WorkoutExerciseData) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var name by remember { mutableStateOf(uiState.initialWorkout?.name ?: "") }
    var date by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var selectedExercises by remember { mutableStateOf("") }
    var showExerciseDropdown by remember { mutableStateOf(false) }
    var tagInput by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf(listOf<String>()) }
    var notes by remember { mutableStateOf("") }
    var isCompleted by remember { mutableStateOf(false) }
    val isLoading = uiState.isLoading
    val keyboardController = LocalSoftwareKeyboardController.current
    

    Box(Modifier.background(LightBackgroundAppColor)) {
        Scaffold(containerColor = Color.Transparent) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .imePadding()
                    .padding(horizontal = spacingS())
                    .background(LightBackgroundAppColor)
            ) {
                // Encabezado
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Create New Workout",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Workout Name
                    WorkoutFormField(label = "Workout Name", isRequired = true) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = { Text("Enter workout name", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF6366F1),
                                unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
                            ),
                            singleLine = true
                        )
                    }

                    // Date & Duration
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            WorkoutFormField(label = "Date") {
                                OutlinedTextField(
                                    value = date,
                                    onValueChange = { date = it },
                                    leadingIcon = {
                                        Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color.Gray)
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            WorkoutFormField(label = "Duration (minutes)") {
                                OutlinedTextField(
                                    value = duration,
                                    onValueChange = { duration = it.filter { ch -> ch.isDigit() } },
                                    placeholder = { Text("Enter duration", color = Color.Gray) },
                                    leadingIcon = {
                                        Icon(Icons.Default.Timer, contentDescription = null, tint = Color.Gray)
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )
                            }
                        }
                    }

                    // Exercises
                    WorkoutFormField(label = "Exercises") {
                        ExposedDropdownMenuBox(
                            expanded = showExerciseDropdown,
                            onExpandedChange = { showExerciseDropdown = it }
                        ) {
                            OutlinedTextField(
                                value = selectedExercises,
                                onValueChange = { },
                                readOnly = true,
                                leadingIcon = {
                                    Icon(Icons.Outlined.FitnessCenter, contentDescription = null, tint = Color.Gray)
                                },
                                trailingIcon = { Icon(Icons.Default.ExpandMore, contentDescription = null, tint = Color.Gray) },
                                modifier = Modifier.fillMaxWidth().menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = showExerciseDropdown,
                                onDismissRequest = { showExerciseDropdown = false }
                            ) {
                                listOf("Push-ups", "Squats", "Deadlifts").forEach { exercise ->
                                    DropdownMenuItem(
                                        text = { Text(exercise) },
                                        onClick = {
                                            selectedExercises = exercise
                                            showExerciseDropdown = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Tags (igual que antes) ...

                    // Notes
                    WorkoutFormField(label = "Notes") {
                        OutlinedTextField(
                            value = notes,
                            onValueChange = { notes = it },
                            placeholder = { Text("Add notes", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                            maxLines = 4
                        )
                    }

                    // Completed
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isCompleted,
                            onCheckedChange = { isCompleted = it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mark as completed")
                    }

                    // Botones
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
                    ) {
                        TextButton(onClick = onDismiss, enabled = !isLoading) {
                            Text("Cancel", color = Color.Gray)
                        }
                        Button(
                            onClick = {
                                onSave(
                                    WorkoutExerciseData(
                                        name = name.trim(),
                                        date = date,
                                        duration = duration,
                                        exercises = selectedExercises,
                                        tags = tags,
                                        notes = notes.trim(),
                                        isCompleted = isCompleted,
                                        id = TODO(),
                                        workoutId = TODO(),
                                        exerciseId = TODO(),
                                        position = TODO(),
                                        exercise = TODO(),
                                        exerciseSets = TODO()
                                    )
                                )
                            },
                            enabled = !nameError && !durationError && !isLoading,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6366F1))
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp, color = Color.White)
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Text("Create Workout")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WorkoutFormField(
    label: String,
    isRequired: Boolean = false,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = if (isRequired) "$label *" else label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
    }
}

@Preview
@Composable
fun AddWorkoutScreenPreview() {
    PreComposeApp {
        KoinApplication(application = {
            modules(previewModule())
        }) {
            val viewModel = koinViewModel<AddWorkoutViewModel>()
            AddWorkoutScreen(
                viewModel = viewModel
            )
        }
    }
}
