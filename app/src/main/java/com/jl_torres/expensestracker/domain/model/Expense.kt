package com.jl_torres.expensestracker.domain.model

import java.time.Instant

data class Expense(
    val id: Int = 0,
    val amount: Double,
    val category: ExpensesCategory,
    val createTime: Instant = Instant.now(),
)
