package com.jmoriba.muscletrack.feature.detail.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.feature.detail.presentation.WorkoutDetailUiState
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_activity
import muscletrack.composeapp.generated.resources.ic_fire
import muscletrack.composeapp.generated.resources.ic_time
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkoutDetailScreen(uiState: WorkoutDetailUiState, workoutId: Int) {
    val workout = uiState.workout ?: return

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
                workout.photo?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = workout.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WorkoutStat(icon = Res.drawable.ic_time, label = "${workout.duration} mins")
                    WorkoutStat(icon =  Res.drawable.ic_fire, label = "${workout.kcal ?: 0} kcal")
                    WorkoutStat(icon =  Res.drawable.ic_activity, label = "${workout.exercises.size} exercises")
                }

                Spacer(modifier = Modifier.height(16.dp))

                workout.notes?.takeIf { it.isNotBlank() }?.let {
                    Text(text = "Notes:", style = MaterialTheme.typography.labelMedium)
                    Text(text = it, modifier = Modifier.padding(top = 4.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                }

                workout.exercises.forEach { exercise ->
                    Text(
                        text = exercise.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    exercise.sets.forEachIndexed { index, set ->
                        Text(
                            text = "Set ${index + 1}: ${set.reps} reps - ${set.weight}kg - RIR ${set.rir}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun WorkoutStat(icon: DrawableResource, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(icon), contentDescription = null, tint = Color.Black)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
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
