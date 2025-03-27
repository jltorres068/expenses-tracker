package com.jl_torres.expensestracker.domain.util

import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

fun Month.getInitialMonthDate(): Instant {
    val currentInstant = Instant.now()
    val currentDate = currentInstant.atZone(ZoneId.systemDefault()).toLocalDate()
    val currentYear = currentDate.year
    val firstDayOfMonth = LocalDate.of(currentYear, this, 1)

    return firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant()
}

fun Month.getLastMonthDate(): Instant {
    val currentInstant = Instant.now()
    val currentDate = currentInstant.atZone(ZoneId.systemDefault()).toLocalDate()
    val currentYear = currentDate.year
    val lastDayOfMonth = LocalDate.of(currentYear, this, 1)
        .with(TemporalAdjusters.lastDayOfMonth())

    return lastDayOfMonth.atTime(23, 59, 59, 999999999)
        .atZone(ZoneId.systemDefault())
        .toInstant()
}