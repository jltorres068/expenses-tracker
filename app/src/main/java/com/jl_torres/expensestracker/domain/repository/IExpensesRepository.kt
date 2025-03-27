package com.jl_torres.expensestracker.domain.repository

import com.jl_torres.expensestracker.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface IExpensesRepository {
    suspend fun createExpense(expense: Expense)
    suspend fun getExpenses(startTime: Instant, endTime: Instant) : Flow<List<Expense>>
}