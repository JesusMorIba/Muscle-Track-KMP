package com.jmoriba.muscletrack.designsystem.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.radarChart.RadarChart
import com.aay.compose.radarChart.model.NetLinesStyle
import com.aay.compose.radarChart.model.Polygon
import com.aay.compose.radarChart.model.PolygonStyle
import com.jmoriba.muscletrack.common.utils.DateUtils
import com.jmoriba.muscletrack.designsystem.component.badge.StatusBadge
import com.jmoriba.muscletrack.designsystem.component.item.DashboardStatItem
import com.jmoriba.muscletrack.designsystem.component.item.WorkoutItem
import com.jmoriba.muscletrack.designsystem.component.row.WorkoutRow
import com.jmoriba.muscletrack.designsystem.theme.Grey200Color
import com.jmoriba.muscletrack.network.model.entities.ExerciseData
import com.jmoriba.muscletrack.network.model.entities.ExerciseSet
import com.jmoriba.muscletrack.network.model.entities.RecentWorkoutData
import com.jmoriba.muscletrack.network.model.entities.StatsData
import com.jmoriba.muscletrack.network.model.entities.WorkoutData
import com.jmoriba.muscletrack.network.model.entities.getCompletionRate
import com.jmoriba.muscletrack.network.model.entities.getDisplayName
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_category
import muscletrack.composeapp.generated.resources.ic_equipment
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardStatsCard(
    stats: StatsData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val completionPercentage =  (stats.getCompletionRate())

            val items = listOf(
                Triple("Total\nWorkouts", stats.totalWorkouts.toString(), "${stats.completedWorkouts} completed") to Pair(Icons.Filled.DateRange, Color(0xFF8B5CF6)),
                Triple("Total\nExercises", stats.totalExercises.toString(), "${stats.exercisesWithPr} with PRs") to Pair(Icons.Filled.FitnessCenter, Color(0xFF8B5CF6)),
                Triple("Completion\nRate", "${completionPercentage.toInt()}%", "") to Pair(Icons.Filled.CheckCircle, Color(0xFF10B981)),
                Triple("Recent\nActivity", stats.recentActivity.toString(), "workouts in the\nlast 7 days") to Pair(Icons.Filled.Bolt, Color(0xFFFBBF24))
            )

            items.forEachIndexed { index, (data, iconData) ->
                DashboardStatItem(
                    title = data.first,
                    value = data.second,
                    subtitle = data.third,
                    icon = iconData.first,
                    iconColor = iconData.second,
                    showProgressBar = index == 2,
                    progressValue = completionPercentage,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun MuscleGroupBalanceCard(
    radarLabels: List<String>,
    muscleValues: List<Double>,
    modifier: Modifier = Modifier
) {
    val primaryColor = Color(0xFF6366F1)
    val filledColor = primaryColor.copy(alpha = 0.3f)
    val netLineColor = Color(0xFFE5E7EB)

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Muscle Group Balance",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Color(0xFF1F2937),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentAlignment = Alignment.Center
            ) {
                RadarChart(
                    modifier = Modifier.fillMaxSize(),
                    radarLabels = radarLabels,
                    labelsStyle = TextStyle(
                        fontSize = 11.sp,
                        color = Color(0xFF6B7280),
                        fontWeight = FontWeight.Medium
                    ),
                    netLinesStyle = NetLinesStyle(
                        netLineColor = netLineColor,
                        netLinesStrokeWidth = 2f,
                        netLinesStrokeCap = StrokeCap.Butt
                    ),
                    scalarSteps = 5,
                    scalarValue = 50.0,
                    scalarValuesStyle = TextStyle(
                        fontSize = 10.sp,
                        color = Color(0xFF9CA3AF)
                    ),
                    polygons = listOf(
                        Polygon(
                            values = muscleValues,
                            unit = "",
                            style = PolygonStyle(
                                fillColor = filledColor,
                                fillColorAlpha = 0.5f,
                                borderColor = primaryColor,
                                borderColorAlpha = 0.8f,
                                borderStrokeWidth = 2f,
                                borderStrokeCap = StrokeCap.Butt
                            )
                        )
                    )
                )
            }
        }
    }
}

@Composable
fun RecentWorkoutsCard(
    workouts: List<RecentWorkoutData>,
    onWorkoutClick: (String) -> Unit,
    onViewAllWorkouts: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB))
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Workouts",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFF1F2937)
                )

                Row(
                    modifier = Modifier
                        .clickable { onViewAllWorkouts() },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6366F1)
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "View all workouts",
                        tint = Color(0xFF6366F1),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            workouts.forEachIndexed { index, workout ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onWorkoutClick(workout.id) }
                ) {
                    WorkoutItem(
                        title = workout.name,
                        date = workout.date,
                        isCompleted = true
                    )
                    if (index != workouts.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            thickness = DividerDefaults.Thickness,
                            color = Color(0xFFE5E7EB)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WorkoutCard(workout: WorkoutData, onWorkoutClick: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = workout.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFF1F2937),
                    modifier = Modifier.weight(1f)
                )
                StatusBadge(isCompleted = workout.isCompleted)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = Color(0xFF6B7280),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = DateUtils.shortDate(workout.date),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = Color(0xFF6B7280),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = workout.duration.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(workout.tags) { tag ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFE0E7FF),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = tag.getDisplayName(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF6366F1)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = null,
                        tint = Color(0xFF6B7280),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${workout.numberOfExercises} exercises",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Repeat,
                        contentDescription = null,
                        tint = Color(0xFF6B7280),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${workout.numberOfSets} sets",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onWorkoutClick(workout.id) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE0E7FF)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null,
                        tint = Color(0xFF6366F1),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "View Details",
                        color = Color(0xFF6366F1),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = { /* Handle delete */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete workout",
                        tint = Color(0xFFEF4444),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseCard(
    exercises: List<ExerciseData>,
    onViewDetails: (ExerciseData) -> Unit,
    onDelete: (ExerciseData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        exercises.forEach { exercise ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = exercise.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = Color(0xFF1F2937),
                            modifier = Modifier.weight(1f)
                        )

                        Box(
                            modifier = Modifier
                                .background(Color(0xFFE0E7FF), shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = exercise.primaryMuscle.getDisplayName(),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF6366F1)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (exercise.secondaryMuscles.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .background(color = Color(0xFFF3F4F6), shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = exercise.secondaryMuscles.joinToString(", "),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF6B7280)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_category),
                            contentDescription = "Exercise Category",
                            tint = Color(0xFF6B7280),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = exercise.category.name,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF6B7280)
                        )

                        exercise.equipment.let { equipment ->
                            Icon(
                                painter = painterResource(Res.drawable.ic_equipment),
                                contentDescription = "Exercise Category",
                                tint = Color(0xFF6B7280),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = equipment.name,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF6B7280)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    /*
                    if (exercise.progress > 0f) {

                        Text(
                            text = "Progress to target",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF6B7280)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ProgressBar(
                                progress = exercise.progress,
                                progressColor = PrimaryColor,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${(exercise.progress * 100).toInt()}%",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF6B7280)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }*/

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { onViewDetails(exercise) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E7FF)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null,
                                tint = Color(0xFF6366F1),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "View Details",
                                color = Color(0xFF6366F1),
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(onClick = { onDelete(exercise) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete exercise",
                                tint = Color(0xFFEF4444),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)
@Composable
fun ExerciseDetailCard(
    exercise: ExerciseData,
    modifier: Modifier = Modifier)
{
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Details",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Description", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(exercise.description ?: "-", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(12.dp))
            Text("Primary Muscle Group", style = MaterialTheme.typography.labelMedium, color = Color.Gray)

            FilterChip(
                selected = false,
                onClick = {},
                label = { Text(exercise.primaryMuscle.getDisplayName()) }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Secondary Muscle Groups", style = MaterialTheme.typography.labelMedium, color = Color.Gray)

            FlowRow(
                modifier = Modifier.padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                exercise.secondaryMuscles.forEach { muscle ->
                    FilterChip(
                        selected = false,
                        onClick = {},
                        label = { Text(muscle.getDisplayName()) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Category", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(exercise.category.name, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Equipment", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(exercise.equipment.name, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(12.dp))
            Text("Performance", style = MaterialTheme.typography.labelMedium, color = Color.Gray)

            /*
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Personal Record", modifier = Modifier.weight(1f))
                Text("${exercise.latestPr ?: 0} kg", color = Color(0xFF4CAF50), fontWeight = FontWeight.SemiBold)
            }*/

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Target", modifier = Modifier.weight(1f))
                Text("${exercise.target ?: 0} kg", color = Color(0xFF9575CD), fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            /*
            if (exercise.progress_chart_data > 0f) {

                Text(
                    text = "Progress to target",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ProgressBar(
                        progress = exercise.progress_chart_data,
                        progressColor = PrimaryColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${(exercise.progress_chart_data * 100).toInt()}%",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF6B7280)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

             */
            }
    }
}

@Composable
fun ExerciseProgressCard(
    exerciseData: ExerciseData,
    modifier: Modifier = Modifier
) {
    /*
    val progressData = exerciseData.progress.filterNotNull()

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Progress",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (progressData.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(vertical = 8.dp)
                ) {
                    LineChart(
                        modifier = Modifier.fillMaxSize(),
                        linesParameters = listOf(
                            LineParameters(
                                label = "Weight Progress",
                                data = progressData.map { it.maxWeight.toDouble() },
                                lineColor = Color(0xFF6366F1),
                                lineType = LineType.CURVED_LINE,
                                lineShadow = true
                            )
                        ),
                        isGrid = true,
                        gridColor = Color(0xFFF3F4F6),
                        xAxisData = progressData.map { dataPoint ->
                            val parts = dataPoint.workoutDate.split("-")
                            if (parts.size >= 3) {
                                val month = parts[1].toIntOrNull() ?: 1
                                val day = parts[2].toIntOrNull() ?: 1
                                val monthName = listOf("Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic")
                                    .getOrNull(month - 1) ?: month.toString()
                                "$monthName $day"
                            } else dataPoint.workoutDate
                        },
                        animateChart = true,
                        showGridWithSpacer = true,
                        yAxisStyle = TextStyle(fontSize = 14.sp, color = Color.Gray),
                        xAxisStyle = TextStyle(fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.W400),
                        yAxisRange = 14,
                        oneLineChart = false,
                        gridOrientation = GridOrientation.VERTICAL
                    )
                }

                if (progressData.size >= 2) {
                    val sortedProgress = progressData.sortedBy { it.workoutDate }
                    val firstWeight = sortedProgress.first().maxWeight
                    val lastWeight = sortedProgress.last().maxWeight
                    val weightChange = lastWeight - firstWeight
                    val roundedChange = (weightChange * 10).toInt() / 10.0
                    val changeColor = when {
                        weightChange > 0 -> Color(0xFF059669)
                        weightChange < 0 -> Color(0xFFDC2626)
                        else -> Color.Gray
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total change: ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF6B7280),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${if (weightChange > 0) "+" else ""}$roundedChange kg",
                            style = MaterialTheme.typography.bodyMedium,
                            color = changeColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }


                if (exerciseData.latestPr != null || exerciseData.target != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        exerciseData.latestPr?.let { pr ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$pr kg",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1F2937),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "Current PR",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF6B7280),
                                    fontSize = 12.sp
                                )
                            }
                        }
                        exerciseData.target?.let { target ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$target kg",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF6366F1),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "Target",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF6B7280),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

            } else {
                EmptyExerciseProgress()
            }
        }
    }

     */
}


@Composable
fun ExerciseWorkoutHistoryCard(
    workouts: List<WorkoutData>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Workout History",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(vertical = 12.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DATE",
                    modifier = Modifier.weight(1.2f),
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                Text(
                    text = "WORKOUT",
                    modifier = Modifier.weight(1.5f),
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                Text(
                    text = "SETS",
                    modifier = Modifier.weight(0.8f),
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "MAX WEIGHT",
                    modifier = Modifier.weight(1f),
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "ACTIONS",
                    modifier = Modifier.weight(1f),
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            Column {
                workouts.forEach { workout ->
                    WorkoutRow(workout)
                    if (workout != workouts.last()) {
                        HorizontalDivider(color = Color(0xFFEEEEEE))
                    }
                }
            }
        }
    }
}

@Composable
fun NotesCard(notes: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Grey200Color, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Notes,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Text(
                text = notes,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun ExerciseSetCard(sets: List<ExerciseSet>) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8F9FA))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("SET", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.weight(0.8f))
                Text("TYPE", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.weight(1f))
                Text("WEIGHT", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.weight(1f))
                Text("REPS", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.weight(0.8f))
                Text("STATUS", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.weight(1.2f))
                Text("ACTIONS", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.weight(1f))
            }

            // Rows
            sets.forEach { set ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = set.setNumber.toString(),
                        fontSize = 14.sp,
                        modifier = Modifier.weight(0.8f)
                    )
                    Text(
                        text = set.type,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = set.weight,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = set.reps.toString(),
                        fontSize = 14.sp,
                        modifier = Modifier.weight(0.8f)
                    )

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (set.isCompleted) Color(0xFF4CAF50).copy(alpha = 0.1f) else Color.Gray.copy(alpha = 0.1f),
                        modifier = Modifier.weight(1.2f)
                    ) {
                        Text(
                            text = if (set.isCompleted) "Completed" else "Pending",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 12.sp,
                            color = if (set.isCompleted) Color(0xFF4CAF50) else Color.Gray,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color(0xFFDC2626),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                if (set != sets.last()) {
                    HorizontalDivider(color = Color(0xFFE5E7EB))
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardStatsCardPreview() {
    Column {
        // DashboardStatsCard(DashboardData.)
    }
}

@Preview
@Composable
fun ExerciseCardPreview() {
    Column {
        /*ExerciseDetailCard(
            exercise = ExerciseData.defaultExercise()
        )*/
    }
}

@Preview
@Composable
fun NotesCardPreview() {
    Column {
        NotesCard(
           notes = "Test Card"
        )
    }
}


