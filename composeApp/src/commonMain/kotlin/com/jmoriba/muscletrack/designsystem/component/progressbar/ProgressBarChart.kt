package com.jmoriba.muscletrack.designsystem.component.progressbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jmoriba.muscletrack.designsystem.component.item.WorkoutHomeItem
import com.jmoriba.muscletrack.designsystem.theme.PrimaryColor
import com.jmoriba.muscletrack.domain.models.WorkoutModelUI
import com.jmoriba.muscletrack.extension.getTodayAbbreviation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Suppress("MemberExtensionConflict")
@Composable
fun ProgressBarChart(
    data: List<WorkoutModelUI> = WorkoutModelUI.defaultWorkoutModelUIList(),
    maxValue: Float = 0f,
    showDetails: Boolean = true,
    onBarClick: (WorkoutModelUI) -> Unit = {}
) {
    val calculatedMaxValue = if (maxValue > 0f) maxValue else data.maxOfOrNull { it.duration.toFloat() }?.plus(10f) ?: 100f
    val todayAbbreviation = remember { getTodayAbbreviation() }
    val todayIndex = data.indexOfFirst { it.date == todayAbbreviation }
    var selectedBarIndex by remember { mutableStateOf(todayIndex) }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(PrimaryColor)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Training minutes",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            data.forEachIndexed { index, item ->
                val animatedHeight by animateFloatAsState(
                    targetValue = item.duration.toFloat() / calculatedMaxValue,
                    animationSpec = tween(1000, delayMillis = index * 100, easing = FastOutSlowInEasing),
                    label = "BarHeight"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable {
                            selectedBarIndex = if (selectedBarIndex == index) -1 else index
                            onBarClick(item)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .width(32.dp)
                            .fillMaxHeight(fraction = animatedHeight)
                            .clip(RoundedCornerShape(4.dp))
                            .background(PrimaryColor)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = item.date,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = if (selectedBarIndex == index) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedBarIndex == index)
                            MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(
            visible = selectedBarIndex >= 0 && showDetails,
            modifier = Modifier,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            selectedBarIndex.takeIf { it in data.indices }?.let { index ->
                val item = data[index]
                WorkoutHomeItem(item, {})
            }
        }
    }
}

@Preview
@Composable
fun ProgressBarChartPreview() {
    Column {
        ProgressBarChart()
    }
}