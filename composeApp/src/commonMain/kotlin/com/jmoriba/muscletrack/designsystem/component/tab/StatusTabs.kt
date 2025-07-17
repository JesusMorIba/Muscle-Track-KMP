package com.jmoriba.muscletrack.designsystem.component.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.jmoriba.muscletrack.network.model.entities.WorkoutFilterEnum

@Composable
fun StatusTabs(
    selectedFilter: WorkoutFilterEnum,
    onFilterChange: (WorkoutFilterEnum) -> Unit
) {
    val tabs = listOf(
        "All Workouts" to WorkoutFilterEnum.ALL_WORKOUTS,
        "Completed" to WorkoutFilterEnum.COMPLETED,
        "In Progress" to WorkoutFilterEnum.IN_PROGRESS
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF3F4F6),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tabs.forEach { (label, value) ->
            val isSelected = selectedFilter == value

            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Color.White else Color.Transparent,
                animationSpec = tween(durationMillis = 150),
                label = "backgroundColor"
            )

            val textColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF111827) else Color(0xFF6B7280),
                animationSpec = tween(durationMillis = 300),
                label = "textColor"
            )

            val scale by animateFloatAsState(
                targetValue = if (isSelected) 1.02f else 1f,
                animationSpec = tween(durationMillis = 200),
                label = "scale"
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .scale(scale)
                    .clickable { onFilterChange(value) }
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                )
            }
        }
    }
}