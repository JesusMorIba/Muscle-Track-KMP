package com.jmoriba.muscletrack.designsystem.component.row

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmoriba.muscletrack.network.model.response.WorkoutData

@Composable
fun WorkoutRow(workout: WorkoutData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = workout.date,
            modifier = Modifier.weight(1.2f),
            fontSize = 14.sp
        )
        Text(
            text = workout.name,
            modifier = Modifier.weight(1.5f),
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = workout.workoutExercises.toString(),
            modifier = Modifier.weight(0.8f),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = workout.workoutExercises.toString(),
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { /* Handle view action */ },
                modifier = Modifier
                    .height(36.dp)
                    .width(80.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8E7FF)
                ),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "View",
                    color = Color(0xFF6366F1),
                    fontSize = 14.sp
                )
            }
        }
    }
}