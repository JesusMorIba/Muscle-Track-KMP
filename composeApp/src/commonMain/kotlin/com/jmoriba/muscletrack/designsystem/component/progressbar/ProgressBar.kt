package com.jmoriba.muscletrack.designsystem.component.progressbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    progressColor: Color = Color(0xFFFBBF24),
    trackColor: Color = Color(0xFFE5E7EB)
) {
    androidx.compose.foundation.Canvas(
        modifier = modifier
    ) {
        val cornerRadius = size.height / 2

        drawRoundRect(
            color = trackColor,
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius)
        )

        if (progress > 0f) {
            drawRoundRect(
                color = progressColor,
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                size = androidx.compose.ui.geometry.Size(size.width * progress, size.height)
            )
        }
    }
}