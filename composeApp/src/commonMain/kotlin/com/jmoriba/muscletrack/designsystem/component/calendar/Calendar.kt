package com.jmoriba.muscletrack.designsystem.component.calendar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmoriba.muscletrack.designsystem.theme.Grey200Color
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Calendar(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    var selectedMonth by remember { mutableStateOf(YearMonth(selectedDate.year, selectedDate.month)) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Grey200Color, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            MonthHeader(
                currentMonth = selectedMonth,
                onPreviousMonth = {
                    selectedMonth = selectedMonth.minusMonths(1)
                },
                onNextMonth = {
                    selectedMonth = selectedMonth.plusMonths(1)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DaysOfWeekHeader()

            Spacer(modifier = Modifier.height(8.dp))

            MonthCalendar(
                currentMonth = selectedMonth,
                selectedDate = selectedDate,
                onDateSelected = { onDateSelected(it) }
            )
        }
    }
}

@Composable
fun MonthHeader(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedContent(
            targetState = currentMonth,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) togetherWith
                        fadeOut(animationSpec = tween(300))
            },
            label = "Month Animation"
        ) { targetMonth ->
            Text(
                text = "${targetMonth.month.name.lowercase().capitalize()} ${targetMonth.year}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            IconButton(onClick = onPreviousMonth) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous Month"
                )
            }

            IconButton(onClick = onNextMonth) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next Month"
                )
            }
        }
    }
}

@Composable
fun DaysOfWeekHeader() {
    val daysOfWeek = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysOfWeek.forEach { day ->
            Text(
                text = day,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun MonthCalendar(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDayOfMonth = LocalDate(currentMonth.year, currentMonth.month, 1)

    val lastDayOfMonth = LocalDate(
        currentMonth.year,
        currentMonth.month,
        currentMonth.month.numberOfDays(currentMonth.year.isLeap())
    )

    val daysToSubtract = (firstDayOfMonth.dayOfWeek.ordinal - DayOfWeek.MONDAY.ordinal + 7) % 7
    val firstDayOfCalendar = firstDayOfMonth.minus(DatePeriod(days = daysToSubtract))

    val calendarDates = mutableListOf<LocalDate>()
    var currentDate = firstDayOfCalendar

    repeat(42) {
        calendarDates.add(currentDate)
        currentDate = currentDate.plus(DatePeriod(days = 1))
    }

    val weeks = calendarDates.chunked(7)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        weeks.forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                week.forEach { date ->
                    val isCurrentMonth = date.month == currentMonth.month
                    val isSelected = date == selectedDate
                    val isToday = date == Clock.System.todayIn(TimeZone.currentSystemDefault())

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isSelected -> MaterialTheme.colorScheme.primary
                                    isToday -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                                    else -> Color.Transparent
                                }
                            )
                            .clickable(enabled = isCurrentMonth) {
                                onDateSelected(date)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            fontSize = 16.sp,
                            fontWeight = if (isSelected || isToday) FontWeight.Bold else FontWeight.Normal,
                            color = when {
                                isSelected -> Color.White
                                !isCurrentMonth -> Color.Gray.copy(alpha = 0.5f)
                                isToday -> MaterialTheme.colorScheme.primary
                                else -> Color.Black
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun String.capitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

@Preview
@Composable
fun CalendarScreenPreview() {
    val currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

    MaterialTheme {
        Calendar(
            selectedDate = currentDate,
            onDateSelected = {}
        )
    }
}
