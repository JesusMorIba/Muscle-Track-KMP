package com.jmoriba.muscletrack.designsystem.component.card

import ExerciseData
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.jmoriba.muscletrack.designsystem.theme.Grey200Color
import com.jmoriba.muscletrack.model.WorkoutModelUI
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_activity
import muscletrack.composeapp.generated.resources.ic_fire
import muscletrack.composeapp.generated.resources.ic_time
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutHistoryCard(
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

@Preview
@Composable
fun WorkoutHistoryCardPreview() {

    Column {
        WorkoutHistoryCard(
            workouts = listOf(WorkoutModelUI.defaultWorkoutModelUI()),
            onWorkoutClick = {}
        )

        WorkoutHistoryCard(
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
