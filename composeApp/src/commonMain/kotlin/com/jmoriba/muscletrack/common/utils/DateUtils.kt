package com.jmoriba.muscletrack.common.utils

import kotlinx.datetime.*

object DateUtils {

    fun shortDate(isoString: String): String = try {
        val date = LocalDate.parse(isoString.split("T")[0])
        "${date.dayOfMonth.toString().padStart(2, '0')}/${date.monthNumber.toString().padStart(2, '0')}/${date.year}"
    } catch (e: Exception) {
        isoString
    }

    fun longDate(isoString: String): String = try {
        val date = LocalDate.parse(isoString.split("T")[0])
        val dayName = getDayName(date.dayOfWeek)
        val monthName = getMonthName(date.month)
        "$dayName, ${date.dayOfMonth} de $monthName de ${date.year}"
    } catch (e: Exception) {
        isoString
    }

    fun dayOfWeek(isoString: String): String = try {
        val date = LocalDate.parse(isoString.split("T")[0])
        getDayName(date.dayOfWeek)
    } catch (e: Exception) {
        ""
    }

    fun monthYear(isoString: String): String = try {
        val date = LocalDate.parse(isoString.split("T")[0])
        val monthName = getMonthName(date.month)
        "$monthName ${date.year}"
    } catch (e: Exception) {
        ""
    }

    fun relativeDate(isoString: String): String = try {
        val workoutDate = LocalDate.parse(isoString.split("T")[0])
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val daysDiff = workoutDate.daysUntil(today)

        when {
            daysDiff == 0 -> "Hoy"
            daysDiff == 1 -> "Ayer"
            daysDiff == -1 -> "Mañana"
            daysDiff > 1 -> "Hace $daysDiff días"
            daysDiff < -1 -> "En ${-daysDiff} días"
            else -> shortDate(isoString)
        }
    } catch (e: Exception) {
        isoString
    }

    fun isToday(isoString: String): Boolean = try {
        val workoutDate = LocalDate.parse(isoString.split("T")[0])
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        workoutDate == today
    } catch (e: Exception) {
        false
    }

    fun isThisWeek(isoString: String): Boolean = try {
        val workoutDate = LocalDate.parse(isoString.split("T")[0])
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val startOfWeek = today.minus(today.dayOfWeek.ordinal, DateTimeUnit.DAY)
        val endOfWeek = startOfWeek.plus(6, DateTimeUnit.DAY)
        workoutDate >= startOfWeek && workoutDate <= endOfWeek
    } catch (e: Exception) {
        false
    }

    fun daysFromToday(isoString: String): Int = try {
        val workoutDate = LocalDate.parse(isoString.split("T")[0])
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        today.daysUntil(workoutDate)
    } catch (e: Exception) {
        0
    }

    private fun getDayName(dayOfWeek: DayOfWeek): String = when (dayOfWeek) {
        DayOfWeek.MONDAY -> "Lunes"
        DayOfWeek.TUESDAY -> "Martes"
        DayOfWeek.WEDNESDAY -> "Miércoles"
        DayOfWeek.THURSDAY -> "Jueves"
        DayOfWeek.FRIDAY -> "Viernes"
        DayOfWeek.SATURDAY -> "Sábado"
        DayOfWeek.SUNDAY -> "Domingo"
        else -> { "" }
    }

    private fun getMonthName(month: Month): String = when (month) {
        Month.JANUARY -> "enero"
        Month.FEBRUARY -> "febrero"
        Month.MARCH -> "marzo"
        Month.APRIL -> "abril"
        Month.MAY -> "mayo"
        Month.JUNE -> "junio"
        Month.JULY -> "julio"
        Month.AUGUST -> "agosto"
        Month.SEPTEMBER -> "septiembre"
        Month.OCTOBER -> "octubre"
        Month.NOVEMBER -> "noviembre"
        Month.DECEMBER -> "diciembre"
        else -> { "" }
    }
}
