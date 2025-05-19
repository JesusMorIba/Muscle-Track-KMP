package com.jmoriba.muscletrack.designsystem.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jmoriba.muscletrack.designsystem.theme.Green
import com.jmoriba.muscletrack.designsystem.theme.Orange
import com.jmoriba.muscletrack.domain.models.WorkoutModelUI
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_arrow_left
import muscletrack.composeapp.generated.resources.ic_fire
import muscletrack.composeapp.generated.resources.ic_time
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutItem (
    workout: WorkoutModelUI,
    onWorkoutClick: (workout: WorkoutModelUI) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onWorkoutClick(workout) }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = workout.photo,
            contentDescription = workout.photo,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = workout.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Stats
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_time),
                        contentDescription = "Duration",
                        tint = Green,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${workout.duration} mins",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_fire),
                        contentDescription = "Calories",
                        tint = Orange,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${workout.kcal} kcal",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }

        Icon(
            painter = painterResource(Res.drawable.ic_arrow_left),
            contentDescription = "View Details",
            tint = Color.Gray
        )
    }
}

@Preview
@Composable
fun WorkoutItemPreview() {
    WorkoutItem(
        workout = WorkoutModelUI(
            id = "1",
            name = "Full-Body HIT Blast",
            photo = "https://i.imgur.com/u3ZefLb.png",
            kcal = 250,
            duration = 20,
            date = "23-03-2000"
        ),
        onWorkoutClick = {}
    )
}