package com.jl_torres.expensestracker.presentation.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.formatDateDayMonthYear(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return this.atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
}