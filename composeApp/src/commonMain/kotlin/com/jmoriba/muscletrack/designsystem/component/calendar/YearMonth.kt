package com.jmoriba.muscletrack.designsystem.component.calendar

import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * A simple data class to represent a year and month combination
 * since kotlinx-datetime doesn't have a direct equivalent to java.time.YearMonth
 */
data class YearMonth(val year: Int, val month: Month) {
    companion object {
        fun now(): YearMonth {
            val now = kotlinx.datetime.Clock.System.now()
            val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
            return YearMonth(localDateTime.year, localDateTime.month)
        }
    }
    /**
     * Returns a new YearMonth that is the specified number of months after this one
     */
    fun plusMonths(months: Int): YearMonth {
        if (months == 0) return this

        // Calculate the total months from year 0 (just for calculation purposes)
        val totalMonths = year * 12 + month.ordinal + months

        // Calculate the new year and month
        val newYear = totalMonths / 12
        val newMonthOrdinal = totalMonths % 12

        // Convert the ordinal back to Month enum
        val newMonth = Month.entries[newMonthOrdinal]

        return YearMonth(newYear, newMonth)
    }

    /**
     * Returns a new YearMonth that is the specified number of months before this one
     */
    fun minusMonths(months: Int): YearMonth {
        return plusMonths(-months)
    }
}

/**
 * Extension function to get the number of days in a month
 */
fun Month.numberOfDays(isLeapYear: Boolean): Int {
    return when (this) {
        Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY,
        Month.AUGUST, Month.OCTOBER, Month.DECEMBER -> 31
        Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
        Month.FEBRUARY -> if (isLeapYear) 29 else 28
        // This else branch should never be reached as all months are covered
        else -> throw IllegalStateException("Unknown month: $this")
    }
}

/**
 * Extension function to check if a year is a leap year
 */
fun Int.isLeap(): Boolean {
    return (this % 4 == 0 && this % 100 != 0) || (this % 400 == 0)
}
