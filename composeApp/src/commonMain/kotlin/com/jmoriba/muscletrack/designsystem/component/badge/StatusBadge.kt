package com.jmoriba.muscletrack.designsystem.component.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusBadge(isCompleted: Boolean) {
    val (backgroundColor, textColor, text) = if (isCompleted) {
        Triple(
            Color(0xFFD1FAE5),
            Color(0xFF10B981),
            "Completed"
        )
    } else {
        Triple(
            Color(0xFFFEF3C7),
            Color(0xFFF59E0B),
            "In Progress"
        )
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}
