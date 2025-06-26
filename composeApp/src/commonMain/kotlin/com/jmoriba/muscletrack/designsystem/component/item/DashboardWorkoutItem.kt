package com.jmoriba.muscletrack.designsystem.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutItem(
    title: String,
    date: String,
    isCompleted: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = Color(0xFFF3F4F6),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Description,
                contentDescription = null,
                tint = Color(0xFF6366F1),
                modifier = Modifier.size(20.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color(0xFF1F2937)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF6B7280)
            )
        }

        val (badgeColor, badgeText, textColor) = if (isCompleted) {
            Triple(Color(0xFFD1FAE5), "Completed", Color(0xFF10B981))
        } else {
            Triple(Color(0xFFFEF3C7), "In Progress", Color(0xFFF59E0B))
        }

        Box(
            modifier = Modifier
                .background(
                    color = badgeColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = badgeText,
                style = MaterialTheme.typography.labelSmall,
                color = textColor
            )
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "View workout details",
            tint = Color(0xFFD1D5DB),
            modifier = Modifier
                .size(20.dp)
                .padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun DashboardWorkoutItemPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        WorkoutItem(
            title = "Push Workout",
            date = "May 28, 2025",
            isCompleted = true
        )
        WorkoutItem(
            title = "Pull Workout",
            date = "May 29, 2025",
            isCompleted = false
        )
    }
}
