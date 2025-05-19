package com.jmoriba.muscletrack.designsystem.component.stat

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jmoriba.muscletrack.designsystem.theme.Typography

@Composable
fun StatRow(label1: String, value1: Int, label2: String, value2: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(label = label1, value = value1)
        StatItem(label = label2, value = value2)
    }
}

@Composable
private fun StatItem(label: String, value: Int) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = label,
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value.toString(),
            style = Typography.titleLarge
        )
    }
}
