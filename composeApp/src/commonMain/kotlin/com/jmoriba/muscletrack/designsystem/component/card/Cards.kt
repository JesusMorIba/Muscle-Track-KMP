package com.jmoriba.muscletrack.designsystem.component.card

import ExerciseData
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmoriba.muscletrack.designsystem.component.item.WorkoutItem
import com.jmoriba.muscletrack.designsystem.component.placeholder.EmptyWorkouts
import com.jmoriba.muscletrack.designsystem.component.stat.StatRow
import com.jmoriba.muscletrack.designsystem.theme.Grey200Color
import com.jmoriba.muscletrack.designsystem.theme.PrimaryColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.domain.models.WorkoutModelUI
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_activity
import muscletrack.composeapp.generated.resources.ic_fire
import muscletrack.composeapp.generated.resources.ic_time
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutReportCard(
    workouts: List<WorkoutModelUI?>,
    onWorkoutClick: (workout: WorkoutModelUI) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .border(1.dp, Grey200Color, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Today, Dec 22",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )


                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_activity),
                            contentDescription = "Activities",
                            tint = Color.Red,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "2",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_time),
                            contentDescription = "Time",
                            tint = Color.Green,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "30 mins",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_fire),
                            contentDescription = "Calories",
                            tint = Color(0xFFFF9500),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "450 kcal",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            HorizontalDivider(Modifier, 1.dp, Grey200Color)

            if (workouts.filterNotNull().isEmpty()) {
                EmptyWorkouts()
            } else {
                workouts.filterNotNull().forEachIndexed { index, workout ->
                    WorkoutItem(workout = workout, onWorkoutClick = onWorkoutClick)
                    if (index != workouts.filterNotNull().lastIndex) {
                        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseCard(
    exercise: ExerciseData,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .border(1.dp, Grey200Color, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = exercise.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (exercise.sets.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "SET",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "WEIGHT",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "REPS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "RIR",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                }

                exercise.sets.forEachIndexed { index, set ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${index + 1}",
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "${set.weight} kg",
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "${set.reps}",
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "${set.rir}",
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (index < exercise.sets.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = Color.LightGray
                        )
                    }
                }
            } else {
                Text(
                    text = "No sets recorded",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun WeeklySummaryCard(
    workouts: List<WorkoutModelUI?>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Grey200Color, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Weekly Summary",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "1–7 mayo",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacingS()))

            StatRow(
                label1 = "Total sessions",
                value1 = workouts.size,
                label2 = "Total duration",
                value2 = workouts.sumOf { it?.duration ?: 0 },
            )

            StatRow(
                label1 = "Estimated calories",
                value1 = workouts.sumOf { it?.kcal?.toInt() ?: 0 },
                label2 = "Average per session",
                value2 = if (workouts.isNotEmpty()) workouts.sumOf { it?.duration ?: 0 } / workouts.size else 0
            )
        }
    }
}

@Preview
@Composable
fun WorkoutHistoryCardPreview() {
    Column {
        WorkoutReportCard(
            workouts = listOf(WorkoutModelUI.defaultWorkoutModelUI()),
            onWorkoutClick = {}
        )
        WorkoutReportCard(
            workouts = emptyList(),
            onWorkoutClick = {}
        )
    }
}

@Preview
@Composable
fun ExerciseCardPreview() {
    Column {
        ExerciseCard(exercise = ExerciseData.defaultExercise())
        ExerciseCard(
            exercise = ExerciseData.defaultExercise().copy(
                sets = emptyList()
            )
        )
    }
}

@Preview
@Composable
fun WeeklySummaryCardPreview() {
    Column {
        WeeklySummaryCard(WorkoutModelUI.defaultWorkoutModelUIList())
    }
}

